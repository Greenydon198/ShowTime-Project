package com.project.ShowTime.Services;

import com.project.ShowTime.DTOs.RequestDTOs.TheatreEntryDto;
import com.project.ShowTime.DTOs.RequestDTOs.TheatreSeatsEntryDto;
import com.project.ShowTime.Enums.SeatType;
import com.project.ShowTime.Exceptions.TheatreNotFound;
import com.project.ShowTime.Models.Show;
import com.project.ShowTime.Models.Theatre;
import com.project.ShowTime.Models.TheatreSeat;
import com.project.ShowTime.Repositories.TheatreRepository;
import com.project.ShowTime.Repositories.TheatreSeatRepository;
import com.project.ShowTime.Transformers.TheatreTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private TheatreSeatRepository theatreSeatRepository;

    public void addTheatre(TheatreEntryDto theatreEntryDto) {
        Theatre theatre = TheatreTransformer.convertDtoToEntity(theatreEntryDto);
        theatreRepository.save(theatre);
    }

    public void addTheatreSeats(TheatreSeatsEntryDto theatreSeatsEntryDto) throws Exception{
        Theatre theatre = null;
        try {
            theatre = theatreRepository.findByLocation(theatreSeatsEntryDto.getLocation());
        }
        catch(Exception e){
            throw new TheatreNotFound("Theatre is not added.");
        }

        if(theatre==null){
            throw new TheatreNotFound("Theatre is not added.");
        }

        List<TheatreSeat> seatList = theatre.getTheatreSeatList();

        if(seatList.size()>0)
            throw new TheatreNotFound("Seats in this Theatre are already added.");

        int i=1;
        for(;i<=theatreSeatsEntryDto.getNoOfClassicRows();i++){
            for(int j=0;j<theatreSeatsEntryDto.getNoOfSeatsInRow();j++){
                TheatreSeat theatreSeat = new TheatreSeat();
                theatreSeat.setTheatre(theatre);
                theatreSeat.setSeatType(SeatType.CLASSIC);
                String seatNo = i+""+(char)('a'+j);//small character to distinguish classic
                theatreSeat.setSeatNo(seatNo);
                seatList.add(theatreSeat);
            }
        }
        for(;i<=theatreSeatsEntryDto.getNoOfClassicRows()+theatreSeatsEntryDto.getNoOfPremiumRows();i++){
            for(int j=0;j<theatreSeatsEntryDto.getNoOfSeatsInRow();j++){
                TheatreSeat theatreSeat = new TheatreSeat();
                theatreSeat.setTheatre(theatre);
                theatreSeat.setSeatType(SeatType.PREMIUM);
                String seatNo = i+""+(char)('A'+j);//
                theatreSeat.setSeatNo(seatNo);
                seatList.add(theatreSeat);
            }
        }
        theatreRepository.save(theatre);
    }

    public List<Theatre> getAll() {
        return theatreRepository.findAll();
    }

    public List<Show> getShowList(int theatreId) throws TheatreNotFound {
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        if(theatreOptional.isEmpty())
            throw new TheatreNotFound("Cannot find the Theatre.");

        return theatreOptional.get().getShowList();
    }

    public List<TheatreSeat> getTheatreSeats(int theatreId) throws TheatreNotFound {
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        if(theatreOptional.isEmpty())
            throw new TheatreNotFound("Cannot find the Theatre.");

        return theatreOptional.get().getTheatreSeatList();
    }
}
