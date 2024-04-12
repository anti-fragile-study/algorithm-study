import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static final Map<String, Folder> folders = new HashMap<>();
    static final Map<String, Info> cache = new HashMap<>();

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; i++) {
            final String input = br.readLine();
            st = new StringTokenizer(input);
            final String parentName = st.nextToken();
            final String name = st.nextToken();
            final boolean isFolder = st.nextToken().equals("1") ? true : false;

            final Folder parent = folders.getOrDefault(parentName, new Folder(parentName));
            if (isFolder) {
                parent.folders.add(name);
            } else {
                parent.files.add(name);
            }
            folders.put(parentName, parent);
            folders.putIfAbsent(name, new Folder(name));
        }

        getCache("main", folders.get("main"));

        final int Q = Integer.parseInt(br.readLine());

        for (int q = 0; q < Q; q++) {
            final String query = br.readLine();
            if (cache.containsKey(query)) {
                final Info info = cache.get(query);
                sb.append(info.files.size()).append(' ').append(info.count).append("\n");
                continue;
            }
            throw new IllegalArgumentException("NO CACHE - " + query);
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static Info getCache(final String key, final Folder folder) {
        cache.put(key, new Info(new HashSet<>(folder.files), folder.files.size()));
        final Info info = cache.get(key);

        for (final String next : folder.folders) {
            final Info nextInfo = getCache(key + '/' + next, folders.get(next));
            info.files.addAll(nextInfo.files);
            info.count += nextInfo.count;
        }

        return info;
    }

    private static class Folder {

        final String name;
        final Set<String> folders;
        final Set<String> files;

        public Folder(final String name) {
            this.name = name;
            this.folders = new HashSet<>();
            this.files = new HashSet<>();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }

        @Override
        public String toString() {
            return "[folders=" + folders + ", files=" + files + "]";
        }
    }

    private static class Info {

        final Set<String> files;
        int count;

        public Info(final Set<String> files, final int count) {
            this.files = files;
            this.count = count;
        }
    }
}
