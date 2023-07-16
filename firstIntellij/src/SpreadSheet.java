public class SpreadSheet {
    protected Cells[][] cell;
    protected int column;
    protected int row;

    SpreadSheet(int cCount, int rCount){
       this.column = cCount;
       this.row = rCount;
       this.cell = new Cells[rCount][cCount];
        for (int i = 0; i < rCount; i++) {
            for (int j = 0; j < cCount; j++) {
                this.cell[i][j] = new Cells();
            }
        }
    }
    protected void addColumn(int position) throws Exception {
        if(position >= 0 && position <= this.row + 1){
            Cells[][] tmpArray = new Cells[this.row][this.column +1];
            for (int i = 0; i < row; i++) {
                System.arraycopy(this.cell[i], 0, tmpArray[i],0, position);
                tmpArray[i][position] = new Cells();
                System.arraycopy(this.cell[i], position, tmpArray[i],position+1, this.cell[i].length - position);

            }
            this.cell = tmpArray;
        }
        else{
            throw new Exception("out of bounds");
        }

    }
    protected void addRow(int position){
        Cells[][] tmpArray = new Cells[this.row+1][this.column];

        System.arraycopy(this.cell, 0, tmpArray,0, position);
        tmpArray[position] = new Cells[this.column];
        for (int i = 0; i < this.column; i++) {
            tmpArray[position][i] = new Cells();
        }
        System.arraycopy(this.cell, position, tmpArray,position+1, this.cell.length - position);

        this.cell = tmpArray;
    }
    protected void setValueAt(int col,int row,Cells cell) throws Exception {
        if(col <= this.column && row <= this.row){
            this.cell[row][col] = cell;
        }
        else{
            throw new Exception("out of bounds");
        }

    }
    protected Cells getValueAt(int col, int row) throws Exception{
        if(col <= this.column && row <= this.row){
            return cell[row][col];
        }
        else{
            throw new Exception("out of bounds");
        }
    }
    public void setColorAt(int col,int row, Colors color) throws Exception {
        if(col <= this.column && row <= this.row){
            this.cell[row][col].setColor(color);
        }
        else{
            throw new Exception("out of bounds");
        }

    }

    public Colors getColorAt(int col,int row) throws Exception {
        if(col <= this.column && row <= this.row){
            return this.cell[row][col].getColor();
        }
        else{
            throw new Exception("out of bounds");
        }

    }

    public void reset() throws Exception {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                cell[i][j] = new Cells();
            }
        }
    }

    public void resetCellAt(int col, int row) throws Exception{
        if(col <= this.column && row <= this.row){
            this.cell[row][col] = new Cells();
        }
        else{
            throw new Exception("out of bounds");
        }
    }
    public int getRowSum(int row) throws Exception {
        int sum = 0;
        if (row > 0 && row < this.row) {
            for (int i = 0; i < this.column; i++) {
                if (cell[row][i].getType() == Types.INTEGER) {
                    sum += Integer.valueOf(cell[row][i].getValue());
                } else continue;
            }
            return sum;
        } else {
            throw new Exception("out of bounds");

        }
    }
    public int getColumnSum(int column) throws Exception{
        int sum = 0;
        if(column >= 0 && column < this.column) {
            for (int i = 0; i < this.row; i++) {
                if (cell[i][column].getType() == Types.INTEGER) {
                    sum += Integer.valueOf(cell[i][column].getValue());
                } else continue;
            }
            return sum;
        }
        else {
            throw new Exception("out of bounds");
        }
    }
    public int getAreaSum(int startColumn, int startRow, int endColumn, int endRow) throws Exception{
        int sum = 0;
        if(startRow < endRow && startRow >= 0 && endRow < this.row && startColumn >= 0 && endColumn < this.column && startColumn < endColumn){
            for (int i = startRow; i <endRow ; i++) {
                for (int j = startColumn; j < endColumn; j++) {
                    if (cell[i][j].getType() == Types.INTEGER) {
                        sum += Integer.valueOf(cell[i][j].getValue());
                    }
                    else continue;
                }
            }
            return sum;
        }
        else {
            throw new Exception("out of bounds");
        }
    }
    public double getRowAverage(int row) throws Exception {
        int count = 0;
        double sum = 0;
        if (row > 0 && row < this.row) {
            for (int i = 0; i < this.column; i++) {
                if (cell[row][i].getType() == Types.INTEGER) {
                    sum += Integer.valueOf(cell[row][i].getValue());
                    count++;
                } else continue;
            }
            if (count != 0)
                return sum / count;
            else
                return 0;
        } else {
            throw new Exception("out of bounds");

        }
    }
    public double getColumnAverage(int column) throws Exception {
        int count = 0;
        double sum = 0;
        if (column > 0 && column < this.column) {
            for (int i = 0; i < this.row; i++) {
                if (cell[column][i].getType() == Types.INTEGER) {
                    sum += Integer.valueOf(cell[column][i].getValue());
                    count++;
                } else continue;
            }
            if (count != 0)
                return sum / count;
            else
                return 0;
        } else {
            throw new Exception("out of bounds");

        }
    }
    public double getAreaAverage(int startColumn, int startRow, int endColumn, int endRow) throws Exception{
        int count = 0;
        double sum=0;
        if(startRow < endRow && startRow >= 0 && endRow < this.row && startColumn >= 0 && endColumn < this.column && startColumn < endColumn){
            for (int i = startRow; i <endRow ; i++) {
                for (int j = startColumn; j < endColumn; j++) {
                    if (cell[i][j].getType() == Types.INTEGER) {
                        sum += Integer.valueOf(cell[i][j].getValue());
                        count ++;
                    }
                    else continue;
                }
            }
            if(count!=0)
                return sum;
            else return 0;
        }
        else {
            throw new Exception("out of bounds");
        }
    }
    private boolean checkBounds(int startColumn, int startRow, int endColumn, int endRow){
        if(startRow < endRow &&
                startColumn < endColumn &&
                startRow < 0 &&
                startColumn < 0 &&
                endColumn >= this.column &&
                endRow >= this.row){
            return false;
        }
        else {
            return true;
        }


    }
}
