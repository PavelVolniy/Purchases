package com.chetverik.excelpoi;

import com.chetverik.domain.contract.Contract;
import com.chetverik.repositories.ContractRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ExcelService {
    private ContractRepo contractRepo;

    public List<Contract> exportContractsToExcel(HttpServletResponse response) throws IOException {
        List<Contract> contracts = (List<Contract>) contractRepo.findAll();
        ExcelExportUtil excelUtil = new ExcelExportUtil(contracts);
        excelUtil.exportDataToExcel(response, "Contracts");
        return contracts;
    }
}
