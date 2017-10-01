package org.option.oi.data;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OIData {
	
	Map<Date, List<Long>> value = new ConcurrentHashMap<Date, List<Long>>();
	
}
