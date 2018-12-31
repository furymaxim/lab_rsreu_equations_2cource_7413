package com.example.avgor.myapplication.HalfDivision;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.example.avgor.myapplication.Format;

public class TableHalfDivision {
    Context context;
    private TableLayout tableLayout;
    private LinearLayout[][] cells;
    private TextView[][] cellsTextView;
    private TableRow[] rows;
    private SolutionHalfDivision methodInfo;
    final static int nColumns = 7;
    int nRows;
    int colorBorderCells, colorBorderTitle, colorTextCells, colorTextTittle, colorBackgroundCells, colorBackgroundTitle;
    String [] titleColumns;
    
    public TableHalfDivision(Context context, TableLayout tableLayout, SolutionHalfDivision methodInfo){
        this.context = context;
        this.tableLayout = tableLayout;
        this.methodInfo = methodInfo;
        this.colorBorderCells = Color.BLACK;
        this.colorBorderTitle = Color.BLACK;
        this.colorTextCells = Color.BLACK;
        this.colorTextTittle = Color.BLACK;
        this.colorBackgroundCells = Color.WHITE;
        this.colorBackgroundTitle = Color.WHITE;
        this.nRows = methodInfo.table.size()+1;
        this.rows = new TableRow[nRows];
        this.cells = new LinearLayout[nRows][nColumns];
        this.cellsTextView  = new TextView[nRows][nColumns];
        titleColumns = new String[]{
                "N",
                "(a+b)/2",
                "f((a+b)/2",
                "a-b",
                "a",
                "b",
                "f(a)"

        };

    }

    public void setColorBorderTitle(int colorBorderTitle) {
        this.colorBorderTitle = colorBorderTitle;
    }

    public void setColorTextTittle(int colorTextTittle) {
        this.colorTextTittle = colorTextTittle;
    }

    public void setColorBackgroundTitle(int colorBackgroundTitle) {
        this.colorBackgroundTitle = colorBackgroundTitle;
    }


    public void setColorBorder(int colorBorderCells) {
        this.colorBorderCells = colorBorderCells;
    }

    public void setColorText(int colorTextCells) {
        this.colorTextCells = colorTextCells;
    }

    public void setColorBackground(int colorBackgroundCells) {
        this.colorBackgroundCells = colorBackgroundCells;
    }


    public void init(){
       
        TableRow.LayoutParams cellParams = new TableRow.LayoutParams(200, 50);
        cellParams.setMargins(2,2,2,2);

        LinearLayout.LayoutParams cellContentParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        cellContentParams.gravity = Gravity.CENTER_HORIZONTAL;

        rows[0] = new TableRow(context);
        rows[0].setBackgroundColor(colorBorderTitle);
        for(int i=0; i<nColumns; i++){
            cells[0][i]= new LinearLayout(context);
            cells[0][i].setBackgroundColor(colorBackgroundTitle);
            cells[0][i].setLayoutParams(cellParams);
            cellsTextView[0][i] = new TextView(context);
            cellsTextView[0][i].setText(titleColumns[i]);
            cellsTextView[0][i].setTextColor(colorTextTittle);
            cellsTextView[0][i].setLayoutParams(cellContentParams);
            cellsTextView[0][i].setGravity(Gravity.CENTER_HORIZONTAL);
            cells[0][i].addView(cellsTextView[0][i]);
            rows[0].addView(cells[0][i]);
        }

        Format format = new Format();
        tableLayout.addView(rows[0]);


        for (int i = 1; i < nRows ; i++) {
            rows[i] = new TableRow(context);
            rows[i].setBackgroundColor(colorBorderCells);
            for (int j = 0; j < nColumns; j++) {
                cells[i][j] = new LinearLayout(context);
                cells[i][j].setBackgroundColor(colorBackgroundCells);
                cells[i][j].setLayoutParams(cellParams);
                cellsTextView[i][j] = new TextView(context);
                String s;
                switch (j) {
                    case 0:
                        s = Integer.toString(i );
                        break;
                    case 1:
                        s = format.double4index(this.methodInfo.table.get(i-1).ab2, context);
                        break;
                    case 2:
                        s = format.double4index(this.methodInfo.table.get(i-1).fab2, context);
                        break;
                    case 3:
                        s = format.double4index(this.methodInfo.table.get(i-1).aMinusB, context);
                        break;
                    case 4:
                        s = format.double4index(this.methodInfo.table.get(i-1).a, context);
                        break;
                    case 5:
                        s = format.double4index(this.methodInfo.table.get(i-1).b, context);
                        break;
                    case 6:
                        s = format.double4index(this.methodInfo.table.get(i-1).fa, context);
                        break;
                    default:
                        s = "";
                        break;
                }
                cellsTextView[i][j].setText(s);
                cellsTextView[i][j].setTextColor(colorTextCells);
                cellsTextView[i][j].setGravity(Gravity.CENTER_HORIZONTAL);
                cellsTextView[i][j].setLayoutParams(cellContentParams);
                cells[i][j].addView(cellsTextView[i][j]);
                rows[i].addView(cells[i][j]);
            }
            tableLayout.addView(rows[i]);
        }
    }

    public static void deleteTable(TableLayout table){
        int count = table.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = table.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }
    }

    public TableLayout getTableLayout() {
        return tableLayout;
    }
}
