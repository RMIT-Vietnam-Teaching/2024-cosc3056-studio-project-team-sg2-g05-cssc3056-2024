# Details

Date : 2024-05-26 14:42:09

Directory c:\\Serious Project\\Programing Studio 1\\terrascope-backend\\src

Total : 38 files,  3092 codes, 2 comments, 616 blanks, all 3710 lines

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [src/main/java/com/xuanduy/terrascope/Config.java](/src/main/java/com/xuanduy/terrascope/Config.java) | Java | 19 | 0 | 3 | 22 |
| [src/main/java/com/xuanduy/terrascope/TerrascopeApplication.java](/src/main/java/com/xuanduy/terrascope/TerrascopeApplication.java) | Java | 9 | 0 | 5 | 14 |
| [src/main/java/com/xuanduy/terrascope/controller/CityController.java](/src/main/java/com/xuanduy/terrascope/controller/CityController.java) | Java | 60 | 0 | 11 | 71 |
| [src/main/java/com/xuanduy/terrascope/controller/CountryController.java](/src/main/java/com/xuanduy/terrascope/controller/CountryController.java) | Java | 88 | 0 | 17 | 105 |
| [src/main/java/com/xuanduy/terrascope/controller/GlobalController.java](/src/main/java/com/xuanduy/terrascope/controller/GlobalController.java) | Java | 18 | 0 | 5 | 23 |
| [src/main/java/com/xuanduy/terrascope/controller/StateController.java](/src/main/java/com/xuanduy/terrascope/controller/StateController.java) | Java | 58 | 0 | 13 | 71 |
| [src/main/java/com/xuanduy/terrascope/dto/city/request/CityRequestDTO.java](/src/main/java/com/xuanduy/terrascope/dto/city/request/CityRequestDTO.java) | Java | 18 | 0 | 7 | 25 |
| [src/main/java/com/xuanduy/terrascope/dto/city/respond/AverageValueOfTemperatureCityRecordDTO.java](/src/main/java/com/xuanduy/terrascope/dto/city/respond/AverageValueOfTemperatureCityRecordDTO.java) | Java | 75 | 0 | 26 | 101 |
| [src/main/java/com/xuanduy/terrascope/dto/city/respond/CityDTO.java](/src/main/java/com/xuanduy/terrascope/dto/city/respond/CityDTO.java) | Java | 38 | 0 | 12 | 50 |
| [src/main/java/com/xuanduy/terrascope/dto/city/respond/CityDataWithinPeriodDTO.java](/src/main/java/com/xuanduy/terrascope/dto/city/respond/CityDataWithinPeriodDTO.java) | Java | 25 | 0 | 10 | 35 |
| [src/main/java/com/xuanduy/terrascope/dto/city/respond/SimilarPeriodCityRecordDTO.java](/src/main/java/com/xuanduy/terrascope/dto/city/respond/SimilarPeriodCityRecordDTO.java) | Java | 90 | 0 | 30 | 120 |
| [src/main/java/com/xuanduy/terrascope/dto/city/respond/TemperatureChangeCityRecordDTO.java](/src/main/java/com/xuanduy/terrascope/dto/city/respond/TemperatureChangeCityRecordDTO.java) | Java | 107 | 0 | 34 | 141 |
| [src/main/java/com/xuanduy/terrascope/dto/country/request/CountryRequestDTO.java](/src/main/java/com/xuanduy/terrascope/dto/country/request/CountryRequestDTO.java) | Java | 24 | 0 | 8 | 32 |
| [src/main/java/com/xuanduy/terrascope/dto/country/respond/AverageValueOfTemperatureCountryRecordDTO.java](/src/main/java/com/xuanduy/terrascope/dto/country/respond/AverageValueOfTemperatureCountryRecordDTO.java) | Java | 54 | 0 | 21 | 75 |
| [src/main/java/com/xuanduy/terrascope/dto/country/respond/CountryDataWithinPeriodDTO.java](/src/main/java/com/xuanduy/terrascope/dto/country/respond/CountryDataWithinPeriodDTO.java) | Java | 26 | 0 | 9 | 35 |
| [src/main/java/com/xuanduy/terrascope/dto/country/respond/CountryRecordDTO.java](/src/main/java/com/xuanduy/terrascope/dto/country/respond/CountryRecordDTO.java) | Java | 45 | 0 | 14 | 59 |
| [src/main/java/com/xuanduy/terrascope/dto/country/respond/RawAndPercentageChangeCountryRecordDTO.java](/src/main/java/com/xuanduy/terrascope/dto/country/respond/RawAndPercentageChangeCountryRecordDTO.java) | Java | 106 | 0 | 35 | 141 |
| [src/main/java/com/xuanduy/terrascope/dto/country/respond/SimilarPeriodCountryRecordDTO.java](/src/main/java/com/xuanduy/terrascope/dto/country/respond/SimilarPeriodCountryRecordDTO.java) | Java | 66 | 0 | 20 | 86 |
| [src/main/java/com/xuanduy/terrascope/dto/global/respond/RawAndPercentageChangeGlobalRecordDTO.java](/src/main/java/com/xuanduy/terrascope/dto/global/respond/RawAndPercentageChangeGlobalRecordDTO.java) | Java | 73 | 0 | 22 | 95 |
| [src/main/java/com/xuanduy/terrascope/dto/state/request/StateRequestDTO.java](/src/main/java/com/xuanduy/terrascope/dto/state/request/StateRequestDTO.java) | Java | 24 | 0 | 8 | 32 |
| [src/main/java/com/xuanduy/terrascope/dto/state/respond/AverageValueOfTemperatureStateRecordDTO.java](/src/main/java/com/xuanduy/terrascope/dto/state/respond/AverageValueOfTemperatureStateRecordDTO.java) | Java | 59 | 0 | 19 | 78 |
| [src/main/java/com/xuanduy/terrascope/dto/state/respond/RawAndPercentageChangeStateRecordDTO.java](/src/main/java/com/xuanduy/terrascope/dto/state/respond/RawAndPercentageChangeStateRecordDTO.java) | Java | 68 | 0 | 20 | 88 |
| [src/main/java/com/xuanduy/terrascope/dto/state/respond/SimilarPeriodStateRecordDTO.java](/src/main/java/com/xuanduy/terrascope/dto/state/respond/SimilarPeriodStateRecordDTO.java) | Java | 74 | 0 | 22 | 96 |
| [src/main/java/com/xuanduy/terrascope/dto/state/respond/StateDTO.java](/src/main/java/com/xuanduy/terrascope/dto/state/respond/StateDTO.java) | Java | 24 | 0 | 8 | 32 |
| [src/main/java/com/xuanduy/terrascope/dto/state/respond/TemperatureChangeStateRecordDTO.java](/src/main/java/com/xuanduy/terrascope/dto/state/respond/TemperatureChangeStateRecordDTO.java) | Java | 93 | 0 | 32 | 125 |
| [src/main/java/com/xuanduy/terrascope/dto/util/request/periodRequestDTO.java](/src/main/java/com/xuanduy/terrascope/dto/util/request/periodRequestDTO.java) | Java | 17 | 0 | 6 | 23 |
| [src/main/java/com/xuanduy/terrascope/dto/util/respond/YearRangeDTO.java](/src/main/java/com/xuanduy/terrascope/dto/util/respond/YearRangeDTO.java) | Java | 17 | 0 | 6 | 23 |
| [src/main/java/com/xuanduy/terrascope/model/Country.java](/src/main/java/com/xuanduy/terrascope/model/Country.java) | Java | 24 | 1 | 7 | 32 |
| [src/main/java/com/xuanduy/terrascope/repository/CityRepository.java](/src/main/java/com/xuanduy/terrascope/repository/CityRepository.java) | Java | 480 | 0 | 39 | 519 |
| [src/main/java/com/xuanduy/terrascope/repository/CountryRepository.java](/src/main/java/com/xuanduy/terrascope/repository/CountryRepository.java) | Java | 497 | 0 | 50 | 547 |
| [src/main/java/com/xuanduy/terrascope/repository/GlobalRepository.java](/src/main/java/com/xuanduy/terrascope/repository/GlobalRepository.java) | Java | 70 | 0 | 10 | 80 |
| [src/main/java/com/xuanduy/terrascope/repository/StateRepository.java](/src/main/java/com/xuanduy/terrascope/repository/StateRepository.java) | Java | 463 | 0 | 29 | 492 |
| [src/main/java/com/xuanduy/terrascope/service/CityService.java](/src/main/java/com/xuanduy/terrascope/service/CityService.java) | Java | 41 | 0 | 13 | 54 |
| [src/main/java/com/xuanduy/terrascope/service/CountryService.java](/src/main/java/com/xuanduy/terrascope/service/CountryService.java) | Java | 64 | 0 | 21 | 85 |
| [src/main/java/com/xuanduy/terrascope/service/GlobalService.java](/src/main/java/com/xuanduy/terrascope/service/GlobalService.java) | Java | 14 | 0 | 5 | 19 |
| [src/main/java/com/xuanduy/terrascope/service/StateService.java](/src/main/java/com/xuanduy/terrascope/service/StateService.java) | Java | 53 | 1 | 13 | 67 |
| [src/main/resources/application.properties](/src/main/resources/application.properties) | Java Properties | 2 | 0 | 1 | 3 |
| [src/test/java/com/xuanduy/terrascope/TerrascopeApplicationTests.java](/src/test/java/com/xuanduy/terrascope/TerrascopeApplicationTests.java) | Java | 9 | 0 | 5 | 14 |

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)