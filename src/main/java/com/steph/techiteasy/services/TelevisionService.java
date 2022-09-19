package com.steph.techiteasy.services;

import com.steph.techiteasy.dtos.TelevisionDto;
import com.steph.techiteasy.dtos.TelevisionInputDto;
import com.steph.techiteasy.exceptions.RecordNotFoundException;
import com.steph.techiteasy.models.Television;
import com.steph.techiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {
    private final TelevisionRepository repos;

    public TelevisionService(TelevisionRepository repos) {
        this.repos = repos;
    }

    public List<TelevisionDto> getAllTelevisions() {
        List<Television> tvList = repos.findAll();
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for(Television tv : tvList) {
            TelevisionDto dto = transferToDto(tv);
            tvDtoList.add(dto);
        }
        return tvDtoList;
    }

    public List<TelevisionDto> getAllTelevisionsByBrand(String brand) {
        List<Television> tvList = repos.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for(Television tv : tvList) {
            TelevisionDto dto = transferToDto(tv);
            tvDtoList.add(dto);
        }
        return tvDtoList;
    }

    public TelevisionDto getTelevisionById(Long id) {
        if(repos.findById(id).isPresent()) {
            Television tv = repos.findById(id).get();
            return transferToDto(tv);
        } else {
            throw new RecordNotFoundException("This ID doesn't exist.");
        }
    }

    public TelevisionDto addTelevision(TelevisionInputDto tvDto) {
        Television tv = transferToTelevision(tvDto);
        repos.save(tv);

        return transferToDto(tv);
    }

    public TelevisionDto updateTelevision(TelevisionInputDto updatedTv, Long id) {
        if(repos.findById(id).isPresent()) {
            Television tv = repos.findById(id).get();

            Television tv1 = transferToTelevision(updatedTv);
            tv1.setId(tv.getId());

            repos.save(tv1);

            return transferToDto(tv1);
        } else {
            throw new RecordNotFoundException("Did not find TV");
        }
    }

    public String deleteTelevision(@RequestBody Long id) {
        if(repos.findById(id).isPresent()) {
            repos.deleteById(id);
            return "TV deleted";
        } else {
            throw new RecordNotFoundException("This ID doesn't exist.");
        }
    }

    public Television transferToTelevision(TelevisionInputDto dto){
        var television = new Television();

        television.setType(dto.getType());
        television.setBrand(dto.getBrand());
        television.setName(dto.getName());
        television.setPrice(dto.getPrice());
        television.setAvailableSize(dto.getAvailableSize());
        television.setRefreshRate(dto.getRefreshRate());
        television.setScreenType(dto.getScreenType());
        television.setScreenQuality(dto.getScreenQuality());
        television.setSmartTv(dto.getSmartTv());
        television.setWifi(dto.getWifi());
        television.setVoiceControl(dto.getVoiceControl());
        television.setHdr(dto.getHdr());
        television.setBluetooth(dto.getBluetooth());
        television.setAmbiLight(dto.getAmbiLight());
        television.setOriginalStock(dto.getOriginalStock());
        television.setSold(dto.getSold());

        return television;
    }

    public TelevisionDto transferToDto(Television television){
        TelevisionDto dto = new TelevisionDto();

        dto.setId(television.getId());
        dto.setType(television.getType());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTv(television.getWifi());
        dto.setWifi(television.getWifi());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setHdr(television.getHdr());
        dto.setBluetooth(television.getBluetooth());
        dto.setAmbiLight(television.getAmbiLight());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setSold(television.getSold());

        return dto;
    }

}
