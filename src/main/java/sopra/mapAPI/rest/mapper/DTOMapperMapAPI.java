package sopra.mapAPI.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sopra.mapAPI.entity.Summit;
import sopra.mapAPI.rest.dto.MapFoundGetDTO;
import sopra.mapAPI.rest.dto.MapSearchPostDTO;

@Mapper
public interface DTOMapperMapAPI {
    DTOMapperMapAPI INSTANCE = Mappers.getMapper(DTOMapperMapAPI.class);

    @Mapping(source = "userInput", target = "name")
    Summit convertMapSearchToSearchText(MapSearchPostDTO searchText);

    //@Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "altitude", target = "altitude")
    MapFoundGetDTO convertEntityToTourGetDTO(Summit summit);
}
