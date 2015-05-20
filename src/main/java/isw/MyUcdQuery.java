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

   public MyUcdQuery() {
       try {
           String instanceName = "rdk-dev";
           String zooServers = "10.71.21.61";
           Instance inst = new ZooKeeperInstance(instanceName, zooServers);
           conn = inst.getConnector("rd_cb_rw", new PasswordToken("opsware"));
       } catch (AccumuloException ae) {
           System.out.println(ae.getMessage());
       } catch (AccumuloSecurityException ase) {
           System.out.println(ase.getMessage());
       }
   }

   public void scan() throws TableNotFoundException {
       String[] au = new String[2];
       au[0] = "U"; au[1] = "FOUO";
        Authorizations auths = new Authorizations(au);
        Scanner scan = conn.createScanner("ucd_artifact", auths);
        //scan.setRange(new Range("3276a62f4712f5206d573c579a9272eb4bfeb8e9943006ce82aa54ff4d548795", "3276a62f4712f5206d573c579a9272eb4bfeb8e9943006ce82aa54ff4d548795"));
        //scan.fetchColumnFamily(new Text("Metadata"));
       scan.setBatchSize(3);
        for (Entry<Key, Value> entry : scan) {
            String row = entry.getKey().getRow().toString();
            System.out.println(entry.getKey().toString());
        }

   }



}