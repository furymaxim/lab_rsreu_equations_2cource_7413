package com.example.avgor.myapplication;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.avgor.myapplication.Exceptions.WrongIntervalException;
import com.example.avgor.myapplication.Exceptions.WrongIntervalFindAnwerException;
import com.example.avgor.myapplication.HalfDivision.SolutionHalfDivision;
import com.example.avgor.myapplication.HalfDivision.TableHalfDivision;
import com.jjoe64.graphview.GraphView;


public class MainActivity extends AppCompatActivity {

    EditText editFunction;
    EditText editGraphLX;
    EditText editGraphRX;
    EditText editSolutionLX;
    EditText editSolutionRX;
    EditText editSolutionError;

    Button buttonFindAnswer;
    Button buttonDrawGraph;
    Button buttonCopyInterval;
    Button buttonSetDefaultInterval;

    String stringFunction;
    double xL;
    double xR;
    TableLayout tableLayoutSolutionHalfDivision;
    SolutionHalfDivision solutionHalfDivision;
    TextView textViewAnswerHalfDivision;

    static final int DEF_X0 = -10;
    static final int DEF_XN = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editGraphLX = findViewById(R.id.textInputLX);
        editGraphRX = findViewById(R.id.textInputRX);
        editSolutionLX = findViewById(R.id.textInputLXA);
        editSolutionRX = findViewById(R.id.textInputRXA);
        editFunction = findViewById(R.id.editFunction);
        editSolutionError = findViewById(R.id.textInputError);
        buttonFindAnswer = findViewById(R.id.buttonFindAnswer);
        tableLayoutSolutionHalfDivision = findViewById(R.id.tableHalfDivisionSolution);
        buttonCopyInterval = findViewById(R.id.buttonCopyTo);
        textViewAnswerHalfDivision = findViewById(R.id.textViewAnswerHalfDivision);
        buttonDrawGraph=  findViewById(R.id.buttonCheckInput);
        buttonSetDefaultInterval = findViewById(R.id.buttonSetDefaultInterval);

        final Graph fGraph  = new Graph((GraphView)(findViewById(R.id.graphXY)),"График фунции:" );
        buttonDrawGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                        stringFunction = editFunction.getText().toString() ;
                        xL = Double.parseDouble((editGraphLX.getText()).toString());
                        xR = Double.parseDouble((editGraphRX.getText()).toString());
                        ValidatorEditText.gapValid(xL, xR);
                    Function f1 = new Function(stringFunction);
                    f1.tab(xL, Math.abs((xL-xR)/50), xR);
                    fGraph.deleteGraphs();
                    fGraph.setGraph(f1.getPoints(),xL, xR,f1.findMin()-f1.scaleY(), f1.findMax()+f1.scaleY() );

                } catch (WrongIntervalException e){
                    ToastMessages.wrongIntervalError(getApplicationContext());
                }
                catch (Exception e) {
                    ToastMessages.dataError(getApplicationContext());
                }

            }
        });
        
        buttonFindAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double xLS, xRS, error;
                Function f1;
                try {
                    stringFunction = editFunction.getText().toString();
                    f1 = new Function(stringFunction);
                    xLS = Double.parseDouble((editSolutionLX.getText()).toString());
                    xRS = Double.parseDouble((editSolutionRX.getText()).toString());
                    ValidatorEditText.gapValid(xLS, xRS);
                    error = Double.parseDouble((editSolutionError.getText()).toString());
                    solutionHalfDivision = new SolutionHalfDivision(f1.function, xLS, xRS, error);
                    drawTableHalfSolution();
                    setAnswerHalfDivision(solutionHalfDivision);

                }
                catch (WrongIntervalFindAnwerException e){
                    ToastMessages.wrongIntervalFindAnswerError(getApplicationContext());
                }
                catch (WrongIntervalException e){
                    ToastMessages.wrongIntervalError(getApplicationContext());
                }
                catch (Exception e) {
                    ToastMessages.programError(getApplicationContext());
                }
            }
        });

        buttonCopyInterval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editSolutionLX.setText((editGraphLX.getText().toString()));
                editSolutionRX.setText((editGraphRX.getText().toString()));

            }
        });

        buttonSetDefaultInterval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editGraphLX.setText(Integer.toString(DEF_X0));
                editGraphRX.setText(Integer.toString(DEF_XN));
            }
        });

    }

    private void drawTableHalfSolution(){
        TableHalfDivision.deleteTable(tableLayoutSolutionHalfDivision) ;
        TableHalfDivision table = new TableHalfDivision( getApplicationContext(), tableLayoutSolutionHalfDivision, solutionHalfDivision);
        table.setColorBackgroundTitle(R.color.colorDarkBlue);
        table.setColorBorder(R.color.colorDarkBlue);
        table.setColorTextTittle(Color.WHITE);
        table.setColorBorderTitle(Color.WHITE);
        table.init();
        tableLayoutSolutionHalfDivision = table.getTableLayout();
    }

    private void setAnswerHalfDivision(SolutionHalfDivision solutionHalfDivision){
        String s = "Ответ: х = ";
        textViewAnswerHalfDivision.setText(s+Double.toString(solutionHalfDivision.getAnswer()));
    }

}
