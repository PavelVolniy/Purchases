package com.chetverik.excelpoi;

import com.chetverik.domain.contract.Contract;
import com.chetverik.domain.contract.ContractFieldNames;
import com.chetverik.domain.purchase.Purchase;
import com.chetverik.domain.purchase.PurchaseFieldNames;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelExportUtil {
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private List<Contract> contractList;
    private List<Purchase> purchaseList;

    public ExcelExportUtil(List<Contract> contractList, List<Purchase> purchaseList) {
        this.contractList = contractList;
        this.purchaseList = purchaseList;
        wb = new HSSFWorkbook();
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        cell.setCellStyle(style);
        sheet.autoSizeColumn(columnCount);
    }

    private void createHeaderRow(String nameSheet, Short heightFont, int countColumns, String[] names) {
        sheet = wb.createSheet(nameSheet);
        Row row = sheet.createRow(0);
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBold(true);
        font.setFontHeight((short) 20);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        createCell(row, 0, nameSheet, style);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, countColumns));
        font.setFontHeightInPoints((short) 10);
        row = sheet.createRow(1);
        font.setBold(true);
        font.setFontHeightInPoints((short) heightFont);
        style.setFont(font);
        for (int i = 0; i < names.length; i++) {
            createCell(row, i, names[i], style);
        }
    }

    private void writeContractData(short heightFont) {
        int rowCount = 2;
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints(heightFont);
        style.setFont(font);

        for (Contract contract : contractList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, contract.getBranch().getName(), style);
            createCell(row, columnCount++, contract.getNameOfContract(), style);
            createCell(row, columnCount++, contract.getPp_poz_ep(), style);
            createCell(row, columnCount++, contract.getTypeOfPurchase().getNameTypeOfPurchase(), style);
            createCell(row, columnCount++, contract.getNumberOfContract(), style);
            createCell(row, columnCount++, contract.getDateOfContract(), style);
            createCell(row, columnCount++, contract.getSum(), style);
            createCell(row, columnCount++, contract.getDateOfExecutionContract(), style);
            createCell(row, columnCount++, contract.getSupplier().getNameSupplier(), style);
            createCell(row, columnCount++, contract.getSupplier().getInn(), style);
            createCell(row, columnCount++, contract.getTypeOfCompany().getNameTypeCompany(), style);
            createCell(row, columnCount++, contract.getNumberOfRegistryEntry(), style);
            createCell(row, columnCount++, contract.getAdditionalAgreement(), style);
            createCell(row, columnCount++, contract.getOkdp2(), style);
            createCell(row, columnCount++, contract.getF_i_o(), style);
            createCell(row, columnCount++, contract.getUser().getUsername(), style);
        }
    }

    private void writePurchaseData(short heightFont) {
        int rowCount = 2;
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints(heightFont);
        style.setFont(font);

        for (Purchase item : purchaseList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, item.getBranch().getName(), style);
            createCell(row, columnCount++, item.getNamePurchase(), style);
            createCell(row, columnCount++, item.getTypeOfPurchase().getNameTypeOfPurchase(), style);
            createCell(row, columnCount++, item.isConditionOfPurchase(), style);
            createCell(row, columnCount++, item.getDateOfPlacement(), style);
            createCell(row, columnCount++, item.getDateOfEnd(), style);
            createCell(row, columnCount++, item.getDateOfReview(), style);
            createCell(row, columnCount++, item.getNumberOfContract(), style);
            createCell(row, columnCount++, item.getStartPrice(), style);
            createCell(row, columnCount++, item.getApplicationAdmitted(), style);
            createCell(row, columnCount++, item.getApplicationSubmitted(), style);
            createCell(row, columnCount++, item.getPriceApplicationOne(), style);
            createCell(row, columnCount++, item.getPriceApplicationTwo(), style);
            createCell(row, columnCount++, item.getDifferenceValues(), style);
            createCell(row, columnCount++, item.getPriceOfContract(), style);
            createCell(row, columnCount++, item.getEconomy(), style);
            createCell(row, columnCount++, item.getNumberOfProcedureOnEIS(), style);
            createCell(row, columnCount++, item.getUser().getUsername(), style);
        }
    }

    public void exportDataToExcel(HttpServletResponse response, String nameSheetOne, String nameSheetTwo) throws IOException {
        createHeaderRow(nameSheetOne,
                (short) 13,
                ContractFieldNames.getContractNames().length,
                ContractFieldNames.getContractNames());
        writeContractData((short) 12);

        createHeaderRow(nameSheetTwo,
                (short) 13,
                PurchaseFieldNames.getContractNames().length,
                PurchaseFieldNames.getContractNames());
        writePurchaseData((short) 12);

        ServletOutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        wb.close();
        outputStream.close();
    }
}
