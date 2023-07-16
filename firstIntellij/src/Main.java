import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws Exception {
        SpreadSheet spreadSheet = new SpreadSheet(10, 10);
            try {
                spreadSheet.setValueAt(1, 3, new Cells(new Value("15"), Colors.RED));
                spreadSheet.setValueAt(1, 3, new Cells(new Value("15"), Colors.RED));
                spreadSheet.addRow(3);
                System.out.println(spreadSheet.getValueAt(0, 4).getValue());
                System.out.println(spreadSheet.getRowSum(4));
                System.out.println(spreadSheet.getValueAt(0, 3).getValue());
                spreadSheet.addColumn(9);
                spreadSheet.setColorAt(0, 4, Colors.YELLOW);
                System.out.println(spreadSheet.getColorAt(0, 4));
                spreadSheet.reset();
                System.out.println(spreadSheet.getColorAt(0, 4));
                spreadSheet.resetCellAt(1, 3);
                System.out.println(spreadSheet.getValueAt(1, 3).getValue());//?
                System.out.println(spreadSheet.getColumnSum(1));
                spreadSheet.setValueAt(0, 3, new Cells(new Value("11-02-2002"), Colors.RED));
                System.out.println(spreadSheet.getValueAt(0, -1).getType());
                spreadSheet.setValueAt(1, 3, new Cells(new Value("-14"), Colors.RED));
                System.out.println(spreadSheet.getAreaSum(0, 1, 3, 5));
                System.out.println(spreadSheet.getColumnAverage(3));
                System.out.println(spreadSheet.getRowAverage(3));
                System.out.println(spreadSheet.getAreaAverage(0, 2, 9, 8));


            } catch (Exception e) {
                System.out.println(e);
            }


    }
}

class Value{
    private String valueString;
    public Value(String number) {
        this.valueString = number;
    }
    void setValue(String value){
        this.valueString = value;
    }
    String getValueString(){
        return valueString;
    }
}

class Cells{
    private Value value;
    private Colors color = Colors.WHITE;
    private Types type = Types.EMPTY;

    Cells(Value value,Colors color){
        this.value = value;
        this.color = color;
        if(value.getValueString().isEmpty())
            type = Types.EMPTY;
        else{
            try {
                Integer.parseInt(value.getValueString());
                type = Types.INTEGER;
            } catch (NumberFormatException e) {
                type = Types.STRING;
                SimpleDateFormat format = new SimpleDateFormat("dd-yyyy-MM");
                try {
                    format.parse(value.getValueString());
                    type = Types.DATE;
                }
                catch(Exception ex){
                    type = Types.STRING;
                }
            }
        }






    }
    Cells(){
        this.value = new Value("");
        this.color = Colors.WHITE;
        this.type = Types.EMPTY;
    }
    public void setValue(Value value){
        this.value=value;
    }
    public String getValue(){
        return value.getValueString();
    }
    public Types getType(){
        return this.type;
    }
    public void setType(Types type){
         this.type = type;
    }
    public void setColor(Colors color){
        this.color = color;
    }
    public Colors getColor() {
        return color;
    }

}

enum Colors{
    RED,
    BLUE,
    YELLOW,
    WHITE
}

enum Types{
    INTEGER,
    STRING,
    DATE,
    EMPTY
}