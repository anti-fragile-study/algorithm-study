import java.io.*;
import java.util.*;

public class Main {
    static Node[] tree = new Node[50];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            char root = stk.nextToken().charAt(0);
            char left = stk.nextToken().charAt(0);
            char right = stk.nextToken().charAt(0);
            tree[root-'A'] = new Node();
            if(left !='.'){
                tree[root-'A'].left = left-'A';
            }else{
                tree[root-'A'].left = -1;
            }
            if(right !='.'){
                tree[root-'A'].right = right-'A';
            }else{
                tree[root-'A'].right = -1;
            }
        }
        preorder(0);
        sb.append('\n');
        inorder(0);
        sb.append('\n');
        postorder(0);

        System.out.println(sb);
    }

    static void postorder(int i){
        if(i==-1) return;
        postorder(tree[i].left);
        postorder(tree[i].right);
        sb.append((char)('A'+i));
    }

    static void preorder(int i){
        if(i==-1) return;
        sb.append((char)('A'+i));
        preorder(tree[i].left);
        preorder(tree[i].right);
    }
    static void inorder(int i){
        if(i==-1) return;
        inorder(tree[i].left);
        sb.append((char)('A'+i));
        inorder(tree[i].right);
    }
    static class Node{
        int left;
        int right;
    }
}