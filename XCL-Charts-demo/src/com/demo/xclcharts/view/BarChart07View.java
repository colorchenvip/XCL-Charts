package com.demo.xclcharts.view;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.xclcharts.chart.BarChart;
import org.xclcharts.chart.BarData;
import org.xclcharts.chart.CustomLineData;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;

public class BarChart07View  extends GraphicalView {
	
	private String TAG = "BarChart04View";
	private BarChart chart = new BarChart();
	//private BarChart3D chart = new BarChart3D();
	//轴数据源
	private List<String> chartLabels = new LinkedList<String>();
	private List<BarData> chartData = new LinkedList<BarData>();
	private List<CustomLineData> mCustomLineDataset = new LinkedList<CustomLineData>();
	
	private float mChartX = 0.0f;
	private float mChartY = 0.0f;
	
	public BarChart07View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		chartLabels();
		chartDataSet();
		chartDesireLines();
		chartRender();
	}
	
	 public BarChart07View(Context context, AttributeSet attrs){   
	        super(context, attrs);   
	        chartLabels();
			chartDataSet();
			chartDesireLines();
			chartRender();
			
	 }
	 
	 
	 public void setChartRange(float x,float y)
	 {
		 mChartX = x;
		 mChartY = y;
	 }
	 
	 
	 public void setChartRange(float x,float y,float width,float height)
	 {
		 chart.setChartRange(x, y, width,height);
		 
		 
	 }
	
	private void chartRender()
	{
		try {
			
			//图所占范围大小
			//chart.setChartRange(0.0f, 0.0f, getScreenWidth(),getScreenHeight());	
			
			chart.setChartRange(0.0f, 0.0f, 450f,650f);
			
			if(chart.isVerticalScreen())
			{
				//chart.setPadding(15, 20, 8, 10);
			}else{
			//	chart.setPadding(20, 20, 10, 8);
			}
					
			//标题
			chart.setTitle("BMI自测");
			chart.addSubtitle("(XCL-Charts Demo)");	
			//数据源
			chart.setDataSource(chartData);
			chart.setCategories(chartLabels);	
			chart.setCustomLines(mCustomLineDataset);
			
			//图例
			//chart.getLegend().setLeftLegend("参考成年男性标准值");
			//chart.getLegend().setLowerLegend("(请不要忽视您的健康)");
			
			//数据轴
			chart.getDataAxis().setAxisMax(40);
			chart.getDataAxis().setAxisMin(0);
			chart.getDataAxis().setAxisSteps(5);		
			//指隔多少个轴刻度(即细刻度)后为主刻度
			chart.getDataAxis().setDetailModeSteps(2);
			
			//背景网格
			chart.getPlotGrid().showHorizontalLines();
			
			//定义数据轴标签显示格式
			chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub		
					Double tmp = Double.parseDouble(value);
					DecimalFormat df=new DecimalFormat("#0");
					String label = df.format(tmp).toString();				
					return (label);
				}
				
			});
			
			//标签旋转45度
			chart.getCategoryAxis().setTickLabelRotateAgent(45f);
			chart.getCategoryAxis().getAxisTickLabelPaint().setTextSize(15);
			
			//在柱形顶部显示值
			chart.getBar().setItemLabelVisible(true);
			//设定格式
			chart.setItemLabelFormatter(new IFormatterDoubleCallBack() {
				@Override
				public String doubleFormatter(Double value) {
					// TODO Auto-generated method stub
					DecimalFormat df=new DecimalFormat("#0");					 
					String label = df.format(value).toString();
					return label;
				}});
			
			//隐藏Key
			chart.getPlotKey().hideKeyLabels();
			
			 //让柱子间没空白
			 chart.getBar().setBarInnerMargin(0.1d); //可尝试0.1或0.5各有啥效果噢
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void chartDataSet()
	{
		//标签对应的柱形数据集
		List<Double> dataSeriesA= new LinkedList<Double>();	
		//依数据值确定对应的柱形颜色.
		List<Integer> dataColorA= new LinkedList<Integer>();	
		
		int max = 35;
	    int min = 15;
	        
		for(int i=1;i<31;i++)
		{
			Random random = new Random();
			int v = random.nextInt(max)%(max-min+1) + min;			 
			dataSeriesA.add((double) v);
			
			if(v <= 18.5d ) //适中
			{
				dataColorA.add((int)Color.rgb(77, 184, 73));				
			}else if(v <= 24d){ //超重
				dataColorA.add((int)Color.rgb(252, 210, 9));
			}else if(v <= 27.9d){ //偏胖
				dataColorA.add((int)Color.rgb(171, 42, 96));	
			}else{  //肥胖
				dataColorA.add((int)Color.RED);
			}
		}  
		//此地的颜色为Key值颜色及柱形的默认颜色
		BarData BarDataA = new BarData("",dataSeriesA,dataColorA,
										(int)Color.rgb(53, 169, 239));
		
		chartData.add(BarDataA);
	}
	
	private void chartLabels()
	{		
		for(Integer i=1;i<31;i++)
		{
			chartLabels.add(Integer.toString(i));
		}
	}	
	
	/**
	 * 期望线/分界线
	 */
	private void chartDesireLines()
	{							
		mCustomLineDataset.add(new CustomLineData("适中",18.5d,(int)Color.rgb(77, 184, 73),3));		
		mCustomLineDataset.add(new CustomLineData("超重",24d,(int)Color.rgb(252, 210, 9),4));
		mCustomLineDataset.add(new CustomLineData("偏胖",27.9d,(int)Color.rgb(171, 42, 96),5));	
		mCustomLineDataset.add(new CustomLineData("肥胖",30d,(int)Color.RED,6));
								
	}
	
	@Override
    public void render(Canvas canvas) {
        try{
            chart.render(canvas);
        } catch (Exception e){
        	Log.e(TAG, e.toString());
        }
    }

	
	
	@Override
	 public void onDraw(Canvas canvas){   	         
	        canvas.drawColor(Color.BLACK);
	        super.onDraw(canvas); 
	 }
	
	//自定义view的宽高
		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			
			 int measuredHeight = measureHeight(heightMeasureSpec);
			 int measuredWidth = measureWidth(widthMeasureSpec);
			 chart.setChartRange(mChartX, mChartY, measuredWidth,measuredHeight);			 
			 setMeasuredDimension(measuredHeight, measuredWidth);
		}
		
		
		private int measureHeight(int measureSpec) {
			 
		    int specMode = MeasureSpec.getMode(measureSpec);
		    int specSize = MeasureSpec.getSize(measureSpec);
		 
		    // Default size if no limits are specified.
		 
		    int result = 500;
		    if (specMode == MeasureSpec.AT_MOST) {
		 
		        // Calculate the ideal size of your
		        // control within this maximum size.
		        // If your control fills the available
		        // space return the outer bound.
		 
		        result = specSize;
		    } else if (specMode == MeasureSpec.EXACTLY) {
		 
		        // If your control can fit within these bounds return that
		        // value.
		        result = specSize;
		    }
		 
		    return result;
		}
		 
		private int measureWidth(int measureSpec) {
		    int specMode = MeasureSpec.getMode(measureSpec);
		    int specSize = MeasureSpec.getSize(measureSpec);
		 
		    // Default size if no limits are specified.
		    int result = 500;
		    if (specMode == MeasureSpec.AT_MOST) {
		        // Calculate the ideal size of your control
		        // within this maximum size.
		        // If your control fills the available space
		        // return the outer bound.
		        result = specSize;
		    }
		 
		    else if (specMode == MeasureSpec.EXACTLY) {
		        // If your control can fit within these bounds return that
		        // value.
		 
		        result = specSize;
		    }
		 
		    return result;
		}
		
}
