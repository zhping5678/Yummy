package com.bl;

import com.blservice.AdminStatistics;
import com.blservice.CustomerStatistics;
import com.blservice.StoreStatistics;
import org.springframework.stereotype.Service;

@Service
public class Statistics implements CustomerStatistics, StoreStatistics, AdminStatistics {
}
