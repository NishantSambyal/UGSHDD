package technician.inteq.com.ugshdd.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Case;
import technician.inteq.com.ugshdd.model.PendingCaseBean.PerformedTaskBean;

/**
 * Created by Nishant Sambyal on 04-Aug-17.
 */

public class CreatePDF {
    double subTotal;
    Image logoImage;
    PdfPCell cell;
    BaseColor myColor1 = WebColors.getRGBColor("#6B4739");
    BaseColor backLight = WebColors.getRGBColor("#EAE6E5");
    Context context;
    List<Case> addItemList, chargeableItemList, returnItemList;
    String customerName;
    private List<PerformedTaskBean> performedList;

    public CreatePDF(Context context, List<Case> addItemList, List<Case> chargeableItemList, List<Case> returnItemList, List<PerformedTaskBean> performedList, String customerName) {
        this.context = context;
        this.addItemList = addItemList;
        this.chargeableItemList = chargeableItemList;
        this.returnItemList = returnItemList;
        this.performedList = performedList;
        this.customerName = customerName;
    }

    public void createPDF(Document document) throws DocumentException {
        createDocument(document);
        this.subTotal = 0;
    }

    private void createDocument(Document document) throws DocumentException {
        Drawable myLogoImage = context.getResources().getDrawable(R.drawable.ugs_logo_black);
        Bitmap bitmapImage = ((BitmapDrawable) myLogoImage).getBitmap();
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, stream2);
        byte[] byteImage = stream2.toByteArray();
        try {
            logoImage = Image.getInstance(byteImage);
            logoImage.setAlignment(Element.ALIGN_RIGHT);
        } catch (IOException | BadElementException e) {
            e.printStackTrace();
        }
        logoImage.scaleAbsolute(150f, 50f);
        float[] columnWidth = {240f, 300f};
        PdfPTable mainTable = new PdfPTable(columnWidth);
        mainTable.setTotalWidth(540f);
        mainTable.setLockedWidth(true);
        mainTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        buildNestedTables(mainTable);

        float[] columnWidthForBlank = {520f};
        PdfPTable detailsTableForBlank = new PdfPTable(columnWidthForBlank);
        detailsTableForBlank.setTotalWidth(520f);
        detailsTableForBlank.setLockedWidth(true);
        detailsTableForBlank.getDefaultCell().setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        buildBlankSpace(detailsTableForBlank);

        float[] summaryWidth = {520f};
        PdfPTable addItemsSummaryTable = new PdfPTable(summaryWidth);
        if (addItemList.size() > 0) {

            addItemsSummaryTable.setTotalWidth(540f);
            addItemsSummaryTable.setLockedWidth(true);
            addItemsSummaryTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            addItemsSummaryTable.getDefaultCell().setBackgroundColor(backLight);
            builtAddItemSummaryTables(addItemsSummaryTable, "ADD ITEMS", addItemList);
        }
        PdfPTable chargeableItemsSummaryTable = new PdfPTable(summaryWidth);
        if (chargeableItemList.size() > 0) {

            chargeableItemsSummaryTable.setTotalWidth(540f);
            chargeableItemsSummaryTable.setLockedWidth(true);
            chargeableItemsSummaryTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            chargeableItemsSummaryTable.getDefaultCell().setBackgroundColor(backLight);
            builtAddItemSummaryTables(chargeableItemsSummaryTable, "CHARGEABLE ITEMS", chargeableItemList);
        }
        PdfPTable returnedItemsSummaryTable = new PdfPTable(summaryWidth);
        if (returnItemList.size() > 0) {
            returnedItemsSummaryTable.setTotalWidth(540f);
            returnedItemsSummaryTable.setLockedWidth(true);
            returnedItemsSummaryTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            returnedItemsSummaryTable.getDefaultCell().setBackgroundColor(backLight);
            builtAddItemSummaryTables(returnedItemsSummaryTable, "RETURNED ITEMS", returnItemList);
        }


        float[] subTotal = {340f, 100f, 100f};
        PdfPTable subTotalSummary = new PdfPTable(subTotal);
        subTotalSummary.setTotalWidth(540f);
        subTotalSummary.setLockedWidth(true);
//        subTotalSummary.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        nestedSubTotal(subTotalSummary);

        float[] performedTasksColumns = {540f};
        PdfPTable performedTasks = new PdfPTable(performedTasksColumns);
        performedTasks.setTotalWidth(540f);
        performedTasks.setLockedWidth(true);
//        performedTasks.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        performedTasks.getDefaultCell().setBackgroundColor(backLight);
        buildNestedPerformedTables(performedTasks);

        float[] addressColumns = {540f};
        PdfPTable addressTable = new PdfPTable(addressColumns);
        addressTable.setTotalWidth(540f);
        addressTable.setLockedWidth(true);
        addressTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        buildAddressTable(addressTable);

        document.add(mainTable);
        document.add(detailsTableForBlank);

        if (addItemList.size() > 0) {
            document.add(addItemsSummaryTable);
            document.add(detailsTableForBlank);
        }

        if (chargeableItemList.size() > 0) {
            document.add(chargeableItemsSummaryTable);
            document.add(detailsTableForBlank);
        }
        if (returnItemList.size() > 0) {
            document.add(returnedItemsSummaryTable);
            document.add(detailsTableForBlank);
        }

        if (this.subTotal > 0) {
            document.add(subTotalSummary);
            document.add(detailsTableForBlank);
        }


        document.add(performedTasks);
        document.add(detailsTableForBlank);

        document.add(addressTable);


        document.newPage();
    }

    private void buildAddressTable(PdfPTable addressTable) {
        Font font = new Font();
        font.setColor(BaseColor.BLACK);
        font.setStyle(Font.BOLD);


        float[] performedWidth = {520f};
        PdfPTable addressTitle = new PdfPTable(performedWidth);
        addressTitle.setTotalWidth(540f);
        addressTitle.setLockedWidth(true);
        addressTitle.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        String dash = "=";
        for (int i = 1; i < 76; i++) {
            dash = dash + "=";
        }
        cell = new PdfPCell(new Phrase(dash, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        addressTitle.addCell(cell);

        cell = new PdfPCell(new Phrase("Address: 16 Defu Lane 9, Singapore 539257\n" +
                "Phone: +65 6382 6663", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        cell.setBorder(Rectangle.NO_BORDER);
        addressTitle.addCell(cell);


        addressTable.addCell(addressTitle);
    }

    private void buildNestedPerformedTables(PdfPTable mainTable) {
        Font font = new Font();
        font.setColor(BaseColor.BLACK);
        font.setStyle(Font.BOLD);

        Font fontTitle = new Font();
        fontTitle.setColor(BaseColor.WHITE);
        fontTitle.setStyle(Font.BOLD);
        fontTitle.setSize(16);

        Font fontWhite = new Font();
        fontWhite.setColor(BaseColor.WHITE);
        fontWhite.setSize(Font.BOLD);
        fontWhite.setSize(14);

        float[] performedWidth = {520f};
        PdfPTable performedTableTitle = new PdfPTable(performedWidth);
        performedTableTitle.setTotalWidth(540f);
        performedTableTitle.setLockedWidth(true);
        performedTableTitle.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        cell = new PdfPCell(new Phrase("PERFORMED TASKS", fontTitle));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(5);
        cell.setBackgroundColor(myColor1);
        performedTableTitle.addCell(cell);
        mainTable.addCell(performedTableTitle);

        float[] columnsWidth = {120f, 300f};
        PdfPTable performedTable = new PdfPTable(columnsWidth);
        performedTable.setTotalWidth(540f);
        performedTable.setLockedWidth(true);
//        performedTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        PdfPTable innerTable = new PdfPTable(1);
        innerTable.setTotalWidth(150f);
        innerTable.setLockedWidth(true);
        innerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        innerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell = new PdfPCell(new Phrase("S.NO", fontWhite));
        cell.setBackgroundColor(myColor1);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setBorder(Rectangle.NO_BORDER);
        innerTable.addCell(cell);
        for (int i = 0; i < performedList.size(); i++) {    //loop for all ITEM numbers numbers
            cell = new PdfPCell(new Phrase(String.valueOf(i + 1), font));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(3);
            innerTable.addCell(cell);

        }
        performedTable.addCell(innerTable);

        PdfPTable innerTable2 = new PdfPTable(1);
        innerTable2.setTotalWidth(380f);
        innerTable2.setLockedWidth(true);
        innerTable2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell(new Phrase("TASKS", fontWhite));
        cell.setBackgroundColor(myColor1);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setBorder(Rectangle.NO_BORDER);
        innerTable2.addCell(cell);
        for (int i = 0; i < performedList.size(); i++) {    //loop for all ITEM numbers numbers
            cell = new PdfPCell(new Phrase(performedList.get(i).getTasks(), font));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPadding(3);
            innerTable2.addCell(cell);

        }

        performedTable.addCell(innerTable2);

        mainTable.addCell(performedTable);

    }


    private void buildNestedTables(PdfPTable outerTable) {
        Font font = new Font(Font.FontFamily.HELVETICA);
        font.setColor(BaseColor.BLACK);
        font.setSize(18);

        Font fontBold = new Font(Font.FontFamily.HELVETICA);
        fontBold.setColor(BaseColor.BLACK);
        fontBold.setStyle(Font.BOLD);
        fontBold.setSize(18);


        PdfPTable innerTable1 = new PdfPTable(1);
        innerTable1.setTotalWidth(150f);
        innerTable1.setLockedWidth(true);
        innerTable1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell();
        cell.addElement(logoImage);//image define prevoiusly
        cell.setBorder(Rectangle.NO_BORDER);
        innerTable1.addCell(cell);

        outerTable.addCell(innerTable1);//table of taxinvoice titles alignment on left

        PdfPTable innerTable2 = new PdfPTable(1);
        innerTable2.setTotalWidth(300f);
        innerTable2.setLockedWidth(true);
        innerTable2.setHorizontalAlignment(Element.ALIGN_RIGHT);


        cell = new PdfPCell(new Phrase("Date :    \t   " + new SimpleDateFormat("yyyy-MM-dd_hh:mm aa", Locale.getDefault()).format(new Date()), font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPaddingBottom(5);

        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPaddingBottom(5);
        innerTable2.addCell(cell);

        cell = new PdfPCell(new Phrase("Outlet ID :   " + UGSApplication.accountNumber, font));
        cell.setBorder(Rectangle.NO_BORDER);
        innerTable2.addCell(cell);
        cell.setPaddingBottom(5);

        cell = new PdfPCell(new Phrase("Username : " + customerName, font));
        cell.setBorder(Rectangle.NO_BORDER);
        innerTable2.addCell(cell);

        outerTable.addCell(innerTable2);//table for image on right side
    }

    private void builtAddItemSummaryTables(PdfPTable summaryTable, String title, List<Case> caseList) {
        Font font = new Font();
        font.setColor(BaseColor.BLACK);
        font.setStyle(Font.BOLD);

        cell = new PdfPCell(new Phrase(title, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        summaryTable.addCell(cell);


        float[] summaryWidth = {50f, 120f, 50f, 50f};
        PdfPTable summaryMainTable = new PdfPTable(summaryWidth);
        summaryMainTable.setTotalWidth(540f);
        summaryMainTable.setLockedWidth(true);
        buildAddItemSummaryNestedTable(summaryMainTable, caseList);
        summaryTable.addCell(summaryMainTable);


        float[] addItemSummaryTotal = {340f, 100f, 100f};
        PdfPTable addItemTotalSummary = new PdfPTable(addItemSummaryTotal);
        addItemTotalSummary.setTotalWidth(540f);
        addItemTotalSummary.setLockedWidth(true);

        PdfPTable innerTable1 = new PdfPTable(1);
        innerTable1.setTotalWidth(80f);
        innerTable1.setLockedWidth(true);
        innerTable1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell(new Phrase());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        innerTable1.addCell(cell);
        addItemTotalSummary.addCell(innerTable1);

        PdfPTable innerTable2 = new PdfPTable(1);
        innerTable2.setTotalWidth(80f);
        innerTable2.setLockedWidth(true);
        innerTable2.setHorizontalAlignment(Element.ALIGN_CENTER);

        cell = new PdfPCell(new Phrase("TOTAL", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        cell.setBorder(Rectangle.NO_BORDER);
        innerTable2.addCell(cell);
        addItemTotalSummary.addCell(innerTable2);

        PdfPTable innerTable3 = new PdfPTable(1);
        innerTable3.setTotalWidth(80f);
        innerTable3.setLockedWidth(true);
        innerTable3.setHorizontalAlignment(Element.ALIGN_CENTER);
        double total = 0;
        for (Case cases : caseList) {
            total += Double.parseDouble(cases.getAmount());
        }
        subTotal = total + subTotal;
        cell = new PdfPCell(new Phrase(Utility.round(total, 2), font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        cell.setBorder(Rectangle.NO_BORDER);
        innerTable3.addCell(cell);
        addItemTotalSummary.addCell(innerTable3);

        summaryTable.addCell(addItemTotalSummary);


    }

    private void buildAddItemSummaryNestedTable(PdfPTable summaryMainTable, List<Case> caseList) {
        Font font = new Font();
        font.setColor(BaseColor.BLACK);
        font.setStyle(Font.BOLD);

        Font fontWhite = new Font();
        fontWhite.setColor(BaseColor.WHITE);
        fontWhite.setSize(Font.BOLD);
        fontWhite.setSize(14);

        PdfPTable innerTable1 = new PdfPTable(1);
        innerTable1.setTotalWidth(100f);
        innerTable1.setLockedWidth(true);
        innerTable1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell(new Phrase("S.NO", fontWhite));
        cell.setBackgroundColor(myColor1);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setBorder(Rectangle.NO_BORDER);
        innerTable1.addCell(cell);
        for (int i = 0; i < caseList.size(); i++) {    //loop for all serial numbers
            cell = new PdfPCell(new Phrase(String.valueOf(i + 1), font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(3);
            cell.setBorder(Rectangle.NO_BORDER);
            innerTable1.addCell(cell);
        }
        summaryMainTable.addCell(innerTable1);

        PdfPTable innerTable2 = new PdfPTable(1);
        innerTable2.setTotalWidth(238f);
        innerTable2.setLockedWidth(true);
        innerTable2.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell = new PdfPCell(new Phrase("ITEM NAME", fontWhite));
        cell.setBackgroundColor(myColor1);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setBorder(Rectangle.NO_BORDER);
        innerTable2.addCell(cell);
        for (int i = 0; i < caseList.size(); i++) {    //loop for all ITEM numbers numbers
            cell = new PdfPCell(new Phrase(caseList.get(i).getInventoryItem().getItem(), font));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(3);
            innerTable2.addCell(cell);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        }
        summaryMainTable.addCell(innerTable2);

        PdfPTable innerTable3 = new PdfPTable(1);
        innerTable3.setTotalWidth(100f);
        innerTable3.setLockedWidth(true);
        innerTable3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell(new Phrase("QUANTITY", fontWhite));
        cell.setBackgroundColor(myColor1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
//        cell.setBorder(Rectangle.NO_BORDER);
        innerTable3.addCell(cell);
        for (int i = 0; i < caseList.size(); i++) {    //loop for all QUANTITY numbers
            cell = new PdfPCell(new Phrase(String.valueOf(caseList.get(i).getQuantity()), font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(3);
            innerTable3.addCell(cell);
        }
        summaryMainTable.addCell(innerTable3);

        PdfPTable innerTable4 = new PdfPTable(1);
        innerTable4.setTotalWidth(100f);
        innerTable4.setLockedWidth(true);
        innerTable4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell(new Phrase("AMOUNT", fontWhite));
        cell.setBackgroundColor(myColor1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
//        cell.setBorder(Rectangle.NO_BORDER);
        innerTable4.addCell(cell);
        for (int i = 0; i < caseList.size(); i++) {    //loop for all Amount numbers
            cell = new PdfPCell(new Phrase(Utility.round(caseList.get(i).getAmount(), 2), font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(3);
            cell.setBorder(Rectangle.NO_BORDER);
            innerTable4.addCell(cell);
        }
        summaryMainTable.addCell(innerTable4);
    }

    private void nestedSubTotal(PdfPTable subTotalSummary) {
        Font font = new Font();
        font.setColor(BaseColor.BLACK);
        font.setStyle(Font.BOLD);
        font.setSize(14);

        PdfPTable innerTable1 = new PdfPTable(1);
        innerTable1.setTotalWidth(80f);
        innerTable1.setLockedWidth(true);
        innerTable1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell(new Phrase());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        innerTable1.addCell(cell);
        subTotalSummary.addCell(innerTable1);

        PdfPTable innerTable2 = new PdfPTable(1);
        innerTable2.setTotalWidth(80f);
        innerTable2.setLockedWidth(true);
        innerTable2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell(new Phrase("SUB TOTAL", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        cell.setBorder(Rectangle.NO_BORDER);
        innerTable2.addCell(cell);
        subTotalSummary.addCell(innerTable2);

        PdfPTable innerTable3 = new PdfPTable(1);
        innerTable3.setTotalWidth(80f);
        innerTable3.setLockedWidth(true);
        innerTable3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell(new Phrase(Utility.round(subTotal, 2), font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        cell.setBorder(Rectangle.NO_BORDER);
        innerTable3.addCell(cell);
        subTotalSummary.addCell(innerTable3);
    }

    private void buildBlankSpace(PdfPTable outerTable) throws DocumentException {

        Font font = new Font();
        font.setColor(BaseColor.WHITE);

        PdfPTable innerTable1 = new PdfPTable(1);
        innerTable1.setTotalWidth(520f);
        innerTable1.setLockedWidth(true);
        innerTable1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell = new PdfPCell(new Phrase("blank space", font));
        cell.setBorder(Rectangle.NO_BORDER);
        innerTable1.addCell(cell);

        outerTable.addCell(innerTable1);
    }
}
