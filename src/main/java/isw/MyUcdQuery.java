package isw;

import org.apache.accumulo.core.client.*;
import org.apache.accumulo.core.client.security.tokens.PasswordToken;
import org.apache.accumulo.core.security.Authorizations;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Range;
import org.apache.accumulo.core.data.Value;
import org.apache.hadoop.io.Text;

import java.util.Map.Entry;



public class MyUcdQuery {
   private Connector conn = null;

   public MyUcdQuery() throws AccumuloException, AccumuloSecurityException {
        String instanceName = "rdk-dev";
        String zooServers = "10.71.21.61";
        Instance inst = new ZooKeeperInstance(instanceName, zooServers);
        conn = inst.getConnector("rd_cb_rw", new PasswordToken("opsware"));
   }

   public void scan() throws TableNotFoundException {
        Authorizations auths = new Authorizations("U,FOUO,USA");
        Scanner scan = conn.createScanner("ucd_artifact", auths);
        scan.setRange(new Range("3276a62f4712f5206d573c579a9272eb4bfeb8e9943006ce82aa54ff4d548795", "3276a62f4712f5206d573c579a9272eb4bfeb8e9943006ce82aa54ff4d548795"));
        scan.fetchColumnFamily(new Text("Metadata"));
        for (Entry<Key, Value> entry : scan) {
            String row = entry.getKey().getRow().toString();
            System.out.println(entry.getKey().toString());
            System.out.println("Hello");
        }

   }



}