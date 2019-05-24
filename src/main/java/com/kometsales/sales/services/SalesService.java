package com.kometsales.sales.services;

import com.kometsales.sales.model.Sales;
import com.kometsales.sales.model.Seller;
import com.kometsales.sales.repository.SalesRepository;
import com.kometsales.sales.repository.SelleRepository;
import com.kometsales.sales.util.CSVUtils;
import com.kometsales.sales.util.UtilList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SalesService {

    private static final Logger LOGGER = LogManager.getLogger(SalesService.class.getName());

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SelleRepository selleRepository;

    @Async
    public CompletableFuture<String> upLoadSales(MultipartFile file) {
        try {
            File outputFile = File.createTempFile("readFile", ".csv");
            file.transferTo(outputFile);

            List<Sales> salesList = CSVUtils.readFileCsv(outputFile);
            List<List<Sales>> lists = UtilList.chopped(salesList, 1000);

            //we go through the list of sublist and Save by pack of 1000 records
            lists.forEach( sales -> salesRepository.saveAll(sales));
            outputFile.deleteOnExit();
        } catch (IOException e) {
            LOGGER.error("Failed to save the sales load ", e);
        }

        List<Seller> sellerList = selleRepository.findAll();
        if (!sellerList.isEmpty()) {
            sellerList.forEach(seller -> {
                //We obtain the sales value of the seller
                BigDecimal totalRound = salesRepository.getTotalRodeBySeller(seller.getId());

                //We calculate the current commissions, when uploading the file
                if (null != totalRound) {
                    Double totalCommission = (seller.getCommissionPercentage() / 100) * totalRound.doubleValue();
                    seller.setCurrentCommissions(new BigDecimal(totalCommission.floatValue()));
                    selleRepository.save(seller);
                }
            });
        }

        return CompletableFuture.completedFuture("They were successfully loaded");
    }



}
