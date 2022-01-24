import java.util.*;

import weka.associations.AssociationRule;
import weka.associations.AssociationRules;
import weka.associations.Apriori;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Apriorii {
    public ArrayList<Double> time_apriori;
    public ArrayList<Double> sup_min_apriori;

    public void weka_Apriori() throws Exception {
        System.out.println("Weka loaded.");

        String dataset = "C:\\Program Files\\Weka-3-9\\data\\supermarket.arff";

        DataSource source = new DataSource(dataset);

        Instances data = source.getDataSet();

        sup_min_apriori= new ArrayList<Double>();
        sup_min_apriori.add(0.05);
        sup_min_apriori.add(0.06);
        sup_min_apriori.add(0.07);
        sup_min_apriori.add(0.08);
        sup_min_apriori.add(0.09);
        sup_min_apriori.add(0.1);
        sup_min_apriori.add(0.11);
        sup_min_apriori.add(0.12);
        sup_min_apriori.add(0.13);
        sup_min_apriori.add(0.14);
        sup_min_apriori.add(0.15);

        List<AssociationRule> ruleList = null;
        time_apriori = new ArrayList<Double>();

        for ( int i = 0; i < sup_min_apriori.size() ; i++) {
            double first_time = System.nanoTime()/1000000000.0;
            Apriori apriori_model = new Apriori();
            String[] options = {"-N", "10000", "-C", "0.9", "-M",Double.toString(sup_min_apriori.get(i))};
            apriori_model.setOptions(options);
            apriori_model.buildAssociations(data);
            AssociationRules ARS = apriori_model.getAssociationRules();
            ruleList = ARS.getRules();

            double time = System.nanoTime()/1000000000.0 -first_time;
            time_apriori.add(time/60.0);

        }

        for (int i = 0; i < ruleList.size(); i++) {
            System.out.println(ruleList.get(i));
        }
        System.out.println(ruleList.size());

        for (int i = 0; i <time_apriori.size() ; i++) {
            System.out.println(time_apriori.get(i));
            System.out.println(sup_min_apriori.get(i));
        }

    }

        public static void main(String[] args) throws Exception {
//        test test = new test();
            Apriorii q = new Apriorii();
            q.weka_Apriori();
        }


}