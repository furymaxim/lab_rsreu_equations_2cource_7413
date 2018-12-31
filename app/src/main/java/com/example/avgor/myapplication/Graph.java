package com.example.avgor.myapplication;

import android.app.Activity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Graph extends Activity {
    GraphView graphXY;


    Graph(GraphView graph, String title){
        graphXY = graph;
        graphXY.setTitle("График указанной функции");
    }

    void  setGraph(DataPoint[] dataPoints, double minX, double maxX, double minY, double maxY){
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        graphXY.getViewport().setYAxisBoundsManual(true);
        graphXY.getViewport().setMinY(minY);
        graphXY.getViewport().setMaxY(maxY);
        graphXY.getViewport().setXAxisBoundsManual(true);
        graphXY.getViewport().setMinX(minX);
        graphXY.getViewport().setMaxX(maxX);
        graphXY.getViewport().setScalable(true);
        graphXY.getViewport().setScalableY(true);
        graphXY.addSeries(series);
        graphXY.getCursorMode();
    }

    void deleteGraphs(){
        graphXY.removeAllSeries();
    }
}
