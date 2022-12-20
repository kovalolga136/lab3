

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private String needle = null;
    private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();

    public GornerTableCellRenderer() {

        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        panel.add(label);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(col == 2){

            label.setText(String.valueOf(value));

            //if(String.valueOf(value) == "true"){
            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            //}

            panel.setBackground(Color.WHITE);
            return panel;
        }

        String formattedDouble = formatter.format(value);
        label.setText(formattedDouble);

        if (needle != null && needle.equals(formattedDouble)) {
            panel.setBackground(Color.RED);
        } else {
            panel.setBackground(Color.WHITE);

            double num = Double.valueOf(label.getText());
            int i = 0;
            String str = String.valueOf(num);
            String finalString = "";
            while(i < str.length() && str.charAt(i) != '.'){
                i++;
            }
            int val = 0;
            int j = i;
            i++;
            for(; i < str.length(); i++){
                val += Integer.valueOf(str.charAt(i)) - Integer.valueOf('0');
            }
            System.out.println(finalString);
            if(val % 10 == 0){
                for(; j < str.length(); j++){
                    System.out.println(str.charAt(j));
                    System.out.println(Integer.valueOf(str.charAt(j)) - Integer.valueOf('0'));

                }
                System.out.println(str);
                System.out.println(val);
                panel.setBackground(Color.ORANGE);
            }
        }

        return panel;
    }
    public void setNeedle(String needle) {
        this.needle = needle;
    }
}
