import weka.associations.AssociationRule;
import weka.associations.AssociationRules;
import weka.associations.BinaryItem;
import weka.associations.FPGrowth;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import weka.associations.AssociationRule;
import weka.associations.AssociationRules;
import weka.associations.BinaryItem;
import weka.associations.FPGrowth;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class Closed_Max_patterns {
    public static void main(String args[])throws Exception{

            double minSup = 0.1;
            int numRules = 1000000;

            String dataset = "C:\\Program Files\\Weka-3-9\\data\\supermarket.arff";
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(dataset);
            //get instance object
            Instances data=source.getDataSet();

            FPGrowth fpgrowth_model=new FPGrowth();
            fpgrowth_model.setNumRulesToFind(numRules);
            fpgrowth_model.setLowerBoundMinSupport(minSup);
            fpgrowth_model.setMinMetric(0);
            fpgrowth_model.buildAssociations(data);
            AssociationRules ARS = fpgrowth_model.getAssociationRules();
            List<AssociationRule> ruleList = ARS.getRules();

            ArrayList<Collection<BinaryItem>> frequentPatterns = new ArrayList<Collection<BinaryItem>>();
            ArrayList<Collection<BinaryItem>> closedPatterns = new ArrayList<Collection<BinaryItem>>();
            ArrayList<Collection<BinaryItem>> maxPatterns = new ArrayList<Collection<BinaryItem>>();
            ArrayList<Integer> support = new ArrayList<Integer>();
            ArrayList<Integer> closed_support = new ArrayList<Integer>();
            ArrayList<Integer> max_support = new ArrayList<Integer>();


            for(int i = 0; i < ruleList.size(); i++) {

                AssociationRule AR = ruleList.get(i);

                Collection premise = AR.getPremise();
                int premiseSupport = AR.getPremiseSupport();

                Collection consequence = AR.getConsequence();
                int consequenceSupport = AR.getConsequenceSupport();

                int totalSupport = AR.getTotalSupport();
                Collection<BinaryItem> baseFrequentPattern = new HashSet<BinaryItem>();

                Iterator iterator = premise.iterator();
                while(iterator.hasNext()) {
                    baseFrequentPattern.add((BinaryItem)iterator.next());
                }

                iterator = consequence.iterator();
                while(iterator.hasNext()) {
                    baseFrequentPattern.add((BinaryItem)iterator.next());
                }

                if(!frequentPatterns.contains(baseFrequentPattern))
                {
                    frequentPatterns.add(baseFrequentPattern);
                    support.add(totalSupport);
                }
            }

        for (int i = 0; i <frequentPatterns.size() ; i++) {

            Collection<BinaryItem> close = frequentPatterns.get(i);
            Integer close_sup = support.get(i);

            Collection<BinaryItem> max = frequentPatterns.get(i);
            Integer max_sup = support.get(i);

            for (int j = 0; j < frequentPatterns.size() ; j++) {

                if(frequentPatterns.get(j).containsAll(close) && support.get(j)>=close_sup)
                {
                    close = frequentPatterns.get(j);
                    close_sup = support.get(j);

                }
                if(frequentPatterns.get(j).containsAll(max))
                {
                    max = frequentPatterns.get(j);
                    max_sup = support.get(j);

                }

            }
            if(!closedPatterns.contains(close))
            {
                closedPatterns.add(close);
                closed_support.add(close_sup);
            }
            if(!maxPatterns.contains(max))
            {
                maxPatterns.add(max);
                max_support.add(max_sup);
            }
        }

        for (int i = 0; i <closedPatterns.size() ; i++) {
            System.out.println("closed-patterns:");
            System.out.println(closedPatterns.get(i));
            System.out.println(closed_support.get(i));
        }
        writeToFile(closedPatterns,closed_support,"closedPatterns");


        for (int i = 0; i <maxPatterns.size() ; i++) {
            System.out.println("max-patterns:");
            System.out.println(maxPatterns.get(i));
            System.out.println(max_support.get(i));
        }

        writeToFile(maxPatterns,max_support,"maxPatterns");

        System.out.println(frequentPatterns.size());
        System.out.println(closedPatterns.size());
        System.out.println(maxPatterns.size());
        }


    public static void writeToFile(ArrayList<Collection<BinaryItem>> array ,ArrayList<Integer> array2, String fileName)
        {
            try {
                FileWriter myWriter = new FileWriter(fileName+".txt");
                String newLine = System.getProperty("line.separator");
                for (int i = 0; i <array.size() ; i++) {
                    myWriter.write(array.get(i)+" "+"support:"+array2.get(i)+newLine);
                }
                myWriter.write("number of patterns:"+array.size()+newLine);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

    }


