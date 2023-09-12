package edu.uci.banerjee.burnserver.services;

import com.univocity.parsers.common.record.Record;

import edu.uci.banerjee.burnserver.model.EscapedFire;
import edu.uci.banerjee.burnserver.model.EscapedFireRepo;
import edu.uci.banerjee.burnserver.model.Fire;
import edu.uci.banerjee.burnserver.model.FiresRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableList;

@Service
@Slf4j
public class DataIngestService {
  private final FiresRepo fireRepo;
  private final EscapedFireRepo escapedFireRepo;
  private final LandOwnershipService landOwnershipService;
  private List<Fire> savedFires;

  public DataIngestService(FiresRepo fireRepo, EscapedFireRepo escapedFireRepo, LandOwnershipService landOwnershipService) {
    this.fireRepo = fireRepo;
    this.escapedFireRepo = escapedFireRepo;
    this.landOwnershipService = landOwnershipService;
  }

  public int saveFires(List<Record> records) {
    log.debug("Saving new fires records.");
    final var burns = records.parallelStream().map(this::createFire).collect(toUnmodifiableList());
    savedFires = fireRepo.saveAll(burns);

    for (int i = 0; i < savedFires.size(); i++) { 
        Record record = records.get(i);
        Fire fire = savedFires.get(i);

        try {
            boolean escapedValue = record.getBoolean("escaped");

            // Create an EscapedFire object with the extracted escaped value
            EscapedFire escapedFire = new EscapedFire(record.getString("name"), escapedValue);

            escapedFire.setFire(fire);
            escapedFireRepo.save(escapedFire);
        } catch (Exception e) {}
    }

    return burns.size();
  }


  private Fire createFire(final Record fireRecord) {
    log.debug("Ingesting record {}", fireRecord);

    final var fire = new Fire();
    fire.setName(fireRecord.getString("name"));
    fire.setAcres(Double.parseDouble(fireRecord.getString("acres")));
    fire.setLatitude(Double.parseDouble(fireRecord.getString("latitude")));
    fire.setLongitude(Double.parseDouble(fireRecord.getString("longitude")));
    fire.setBurnType(fireRecord.getString("burn_type"));
    fire.setTreatmentType(fireRecord.getString("treatment_type"));
    fire.setCountyUnitId(fireRecord.getString("county_unit_ID"));
    fire.setCounty(fireRecord.getString("county"));
    fire.setSource(fireRecord.getString("source"));
    fire.setFireType(fireRecord.getString("fire_type"));
    // landOwnershipService will give an exception to every fire entry if not connected
    fire.setOwner(landOwnershipService.getOwnershipFromCoordinate(fire.getLatitude(), fire.getLongitude()));
    //fire.setOwner("");

    try {
      final var fireDate = Calendar.getInstance();
      fireDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse(fireRecord.getString("date")));
      fire.setYear(fireDate.get(Calendar.YEAR));
      fire.setMonth(fireDate.get(Calendar.MONTH));
      fire.setDay(fireDate.get(Calendar.DAY_OF_MONTH));
    } catch (ParseException e) {
      log.warn("Record : " + fireRecord.toString());
      log.warn("Date is Invalid. " + e.getMessage());
    }

    return fire;
  }


}
