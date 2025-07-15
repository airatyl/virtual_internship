package org.airat.travel.insurance.core.services;

import lombok.RequiredArgsConstructor;
import org.airat.travel.insurance.core.domain.*;
import org.airat.travel.insurance.core.repositories.*;
import org.airat.travel.insurance.dto.v2.PersonDTO;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AgreementsEntityService {

    private final AgreementRepository agreementRepository;
    private final AgreementPersonRepository agreementPersonRepository;
    private final PersonRepository personRepository;
    private final SelectedRiskRepository selectedRiskRepository;
    private final RiskTypeRepository riskTypeRepository;
    private final AgreementPersonRiskRepository agreementPersonRiskRepository;


    public Agreement saveAll(TravelCalculatePremiumResponseV2 responseV2,List<String> risks){
        Agreement agreement = saveAgreement(responseV2);
        savePeople(responseV2,agreement);
        saveRisks(agreement,risks);
        return agreement;
    }

    private Agreement saveAgreement(TravelCalculatePremiumResponseV2 responseV2){
        Agreement agreement =new Agreement();
        agreement.setPremium(responseV2.getAgreementPremium());
        agreement.setCountry(responseV2.getCountry());
        agreement.setDateTo(responseV2.getAgreementDateTo());
        agreement.setDateFrom(responseV2.getAgreementDateFrom());
        agreement.setUuid(UUID.randomUUID().toString());
        return agreementRepository.save(agreement);
    }

    private void savePeople(TravelCalculatePremiumResponseV2 responseV2,Agreement agreement){
        responseV2.getPeople().forEach(personDTO -> {

            Person savedPerson =savePerson(personDTO);
            AgreementPerson agreementPerson =
            saveAgreementPerson(savedPerson,agreement,personDTO.getMedicalRiskLimitLevel().doubleValue());
            saveAgreementPersonRisk(agreementPerson,personDTO);

        });
    }
    private Person savePerson(PersonDTO personDTO){
        Person findedPerson= personRepository.findByFirstNameAndLastNameAndPersonCode
                (personDTO.getPersonFirstName(), personDTO.getPersonLastName(),
                        personDTO.getPersonCode());
        if (findedPerson!=null){
            return findedPerson;
        }
        else {
            Person person = new Person();
            person.setBirthDate(personDTO.getBirthDate());
            person.setPersonCode(personDTO.getPersonCode());
            person.setFirstName(personDTO.getPersonFirstName());
            person.setLastName(personDTO.getPersonLastName());
            return personRepository.save(person);
        }
    }

    private void saveRisks(Agreement agreement,List<String> risks){
        risks.forEach(risk ->{
            SelectedRisk selectedRisk =new SelectedRisk();
            selectedRisk.setAgreement(agreement);
            selectedRisk.setRisk(riskTypeRepository.findByTitle(risk));
            selectedRiskRepository.save(selectedRisk);
        });
    }
    public AgreementPerson saveAgreementPerson(Person person,Agreement agreement,double medicalRiskLimit){
        AgreementPerson agreementPerson =new AgreementPerson();
        agreementPerson.setPerson(person);
        agreementPerson.setAgreement(agreement);
        agreementPerson.setMedicalRiskLimitLevel(medicalRiskLimit);
        return agreementPersonRepository.save(agreementPerson);
    }
    private void saveAgreementPersonRisk(AgreementPerson agreementPerson,PersonDTO personDTO){
        personDTO.getResult().riskPremiums().forEach(riskPremium -> {
            AgreementPersonRisk agreementPersonRisk =new AgreementPersonRisk();
            agreementPersonRisk.setAgreementPerson(agreementPerson);
            agreementPersonRisk.setRisk(riskTypeRepository.findByTitle(riskPremium.getRisk()));
            agreementPersonRisk.setPremium(riskPremium.getPremium());
            agreementPersonRiskRepository.save(agreementPersonRisk);
        });

    }
}
