import java.io.*;
import java.util.*;

public class Main {

    static class Element implements Comparable<Element>{
        int idx, dist;

        public Element(int idx, int dist){
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Element e){
            return Integer.compare(this.dist, e.dist);
        }
    }

    static int N, E;
    static ArrayList<Element>[] graph;

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 0 ; i <= N ; i ++){
            graph[i] = new ArrayList<>();
        }
        for (int i = 0 ; i < E; i ++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            graph[from].add(new Element(to, dist));
            graph[to].add(new Element(from, dist));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int ans = Integer.MAX_VALUE;

        int result1 = dijks(1, v1) + dijks(v1, v2) + dijks(v2, N);
        int result2 = dijks(1,v2) + dijks(v2, v1) + dijks(v1, N);
        ans = Math.min(result1, result2);
        if(ans < 0) ans = -1;
        System.out.println(ans);
    }

    public static int dijks(int start, int end){
        int[] dist = new int[N + 1];
        Arrays.fill(dist, (int)1e9);
        dist[start] = 0 ;
        PriorityQueue<Element> pq = new PriorityQueue<>();
        pq.add(new Element(start, 0));

        while(!pq.isEmpty()){
            Element cur = pq.poll();
            int minIdx = cur.idx;
            int minDist = cur.dist;

            if(dist[minIdx] != minDist) continue;

            for(Element e : graph[minIdx]){
                int targetIdx = e.idx;
                int targetDist = e.dist;
                int newDist = targetDist + dist[minIdx];

                if(dist[targetIdx] > newDist){
                    dist[targetIdx] = newDist;
                    pq.add(new Element(targetIdx, newDist));
                }
            }
        }
        if(dist[end] == (int)1e9) dist[end] = Integer.MIN_VALUE;
        return dist[end];
    }
    
}
