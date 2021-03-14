package cfg;

import org.antlr.v4.runtime.misc.MultiMap;
import org.stringtemplate.v4.ST;

import java.util.HashMap;
import java.util.Map;

public class GraphViz {
    public HashMap<String,Boolean> nodes;
    public MultiMap<String, String> edges;

    public GraphViz(){
        // use org.antlr.v4.runtime.misc: OrderedHashSet, MultiMap
        this.nodes = new HashMap<String,Boolean>(); // list of functions
        this.edges = new MultiMap<String, String>();  // caller->callee
    }

    public void edge(String source, String target) {
        edges.map(source, target);
    }
    public String toString() {
        return "edges: "+edges.toString()+", nodes: "+ nodes;
    }
    public String toDOT() {
        StringBuilder buf = new StringBuilder();
        buf.append("digraph G {\n");
        buf.append("  ranksep=.25;\n");
        buf.append("  edge [arrowsize=.5]\n");
        buf.append("  node [shape=rectangle, fontname=\"ArialNarrow\",\n");
        buf.append("        fontsize=12,  height=1.2];\n");
        buf.append("  ");
        for (Map.Entry<String,Boolean> entry : nodes.entrySet()) { // print all nodes first
            buf.append("\"").append(entry.getKey()).append("\"");
            if(entry.getValue()){
                buf.append("[color=red]");
            }
            buf.append("; ");
        }
        buf.append("\n");
        for (String src : edges.keySet()) {
            for (String trg : edges.get(src)) {
                buf.append("  ");
                buf.append("\"").append(src).append("\"");
                buf.append(" -> ");
                buf.append("\"").append(trg.split("---")[0]).append("\"");
                buf.append("  [label=").append("\"").append(trg.split("---")[1]).append("\"]");
                buf.append(";\n");
            }
        }
        buf.append("}\n");
        return buf.toString();
    }

    public String toDOT2() {
        StringBuilder buf = new StringBuilder();
        buf.append("digraph G {\n");
        buf.append("  ranksep=.25;\n");
        buf.append("  edge [arrowsize=.5]\n");
        buf.append("  node [shape=circle, fontname=\"ArialNarrow\",\n");
        buf.append("        fontsize=12,  height=1.2];\n");
        buf.append("  ");
        for (String node : nodes.keySet()) { // print all nodes first
            buf.append("\"").append(node).append("\"");
            buf.append("; ");
        }
        buf.append("\n");
        for (String src : edges.keySet()) {
            for (String trg : edges.get(src)) {
                buf.append("  ");
                buf.append("\"").append(src).append("\"");
                buf.append(" -> ");
                buf.append("\"").append(trg).append("\"");
                buf.append(";\n");
            }
        }
        buf.append("}\n");
        return buf.toString();
    }

    /** Fill StringTemplate:
     digraph G {
     rankdir=LR;
     <edgePairs:{edge| <edge.a> -> <edge.b>;}; separator="\n">
     <childless:{f | <f>;}; separator="\n">
     }

     Just as an example. Much cleaner than buf.append method
     */
    public ST toST() {
        ST st = new ST(
                "digraph G {\n" +
                        "  ranksep=.25; \n" +
                        "  edge [arrowsize=.5]\n" +
                        "  node [shape=circle, fontname=\"ArialNarrow\",\n" +
                        "        fontsize=12, fixedsize=true, height=.45];\n" +
                        "  <nodes:{n | <n>; }>\n" +
                        "  <edgePairs:{edge| <edge.a> -> <edge.b>;}; separator=\"\\n\">\n" +
                        "}\n"
        );
        st.add("edgePairs", edges.getPairs());
        st.add("nodes", nodes);
        return st;
    }


}
