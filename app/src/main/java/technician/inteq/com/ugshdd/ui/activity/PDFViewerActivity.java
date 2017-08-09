package technician.inteq.com.ugshdd.ui.activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

import technician.inteq.com.ugshdd.R;

public class PDFViewerActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        pdfView = (PDFView) findViewById(R.id.pdfViewPager);
        pdfView.fromFile(new File(getIntent().getExtras().get("filepath").toString().trim()))
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                    }
                })
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {

                    }
                })
                .onPageChange(new OnPageChangeListener() {
                                  @Override
                                  public void onPageChanged(int page, int pageCount) {

                                  }
                              }
                )
                .load();

    }
}
