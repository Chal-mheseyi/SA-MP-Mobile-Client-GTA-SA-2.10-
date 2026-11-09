//
// // Created on by NoobCooder 14.04.2023.
//

#include "CFileMgr.h"
#include "main.h"
#include "util/patch.h"
#include <dirent.h>
#include <strings.h>

void CFileMgr::SetDir(const char *path) {
    ( ( void(*)(const char *path) )(g_libGTASA + (VER_x32 ? 0x003F0C54 + 1 : 0x4D293C)) )(path);
}

// Android file names are case-sensitive: find "rel" below "root" ignoring upper/lower case.
static bool FindIgnoreCasePath(const char* root, const char* rel, char* out, size_t outSize)
{
    char cur[512];
    snprintf(cur, sizeof(cur), "%s", root);
    char part[256];
    const char* p = rel;

    while (*p)
    {
        while (*p == '/') p++;
        if (!*p) break;

        size_t n = 0;
        while (p[n] && p[n] != '/') n++;
        if (n == 0 || n >= sizeof(part)) return false;
        memcpy(part, p, n);
        part[n] = 0;
        p += n;

        DIR* d = opendir(cur);
        if (!d) return false;

        bool found = false;
        struct dirent* e;
        while ((e = readdir(d)) != nullptr)
        {
            if (strcasecmp(e->d_name, part) == 0)
            {
                size_t len = strlen(cur);
                snprintf(cur + len, sizeof(cur) - len, "%s%s",
                         (len > 0 && cur[len - 1] == '/') ? "" : "/", e->d_name);
                found = true;
                break;
            }
        }
        closedir(d);
        if (!found) return false;
    }

    snprintf(out, outSize, "%s", cur);
    return true;
}

FILE* CFileMgr::OpenFile(const char *path, const char *mode) {
    // The game code passes Windows-style paths ("DATA\\WEAPON.DAT"). Android needs '/'.
    char rel[256];
    snprintf(rel, sizeof(rel), "%s", path);
    for (char* c = rel; *c; ++c) {
        if (*c == '\\') *c = '/';
    }

    // Try SA-MP's own weapon.dat first (same redirect as the file-open hook), then the normal path.
    const char* candidates[2];
    int count = 0;
    if (!strncasecmp(rel, "DATA/WEAPON.DAT", 15)) {
        candidates[count++] = "SAMP/weapon.dat";
    }
    candidates[count++] = rel;

    FILE* file = nullptr;
    for (int i = 0; i < count && !file; i++) {
        snprintf(ms_path, sizeof(ms_path), "%s%s", g_pszStorage, candidates[i]);
        file = fopen(ms_path, mode);

        if (!file) {
            char fixed[512];
            if (FindIgnoreCasePath(g_pszStorage, candidates[i], fixed, sizeof(fixed))) {
                file = fopen(fixed, mode);
            }
        }
    }

    if(!file) {
        Log("Fail open file %s", ms_path);
    }
    return file;
}

int32_t CFileMgr::CloseFile(FILE* file) {
    return fclose(file);
}

void CFileMgr::Initialise() {
    CHook::CallFunction<void>("_ZN8CFileMgr10InitialiseEv"); // ������� ����� �����
}
