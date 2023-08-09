package com.chetverik.service;

import com.chetverik.domain.contract.Contract;
import com.chetverik.domain.purchase.Purchase;
import com.chetverik.excelpoi.ExcelExportUtil;
import com.chetverik.repositories.ContractRepo;
import com.chetverik.repositories.PurchaseRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ExcelService {
    private ContractRepo contractRepo;
    private PurchaseRepo purchaseRepo;

    public List<Contract> exportContractsToExcel(HttpServletResponse response) throws IOException {
        List<Contract> contracts = (List<Contract>) contractRepo.findAll();
        List<Purchase> purchases = (List<Purchase>) purchaseRepo.findAll();
        ExcelExportUtil excelUtil = new ExcelExportUtil(contracts, purchases);
        excelUtil.exportDataToExcel(response, "Contracts", "Purchases");
        return contracts;
    }
}
