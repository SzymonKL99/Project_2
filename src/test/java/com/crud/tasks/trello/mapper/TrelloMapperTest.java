package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void shouldMapTrelloBoardDtoListToTrelloBoardList() {
        // Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "Trello List Dto 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "Trello List Dto 2", false);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "Trello Board 1", new ArrayList<>(
                List.of(trelloListDto, trelloListDto2)
        ));
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>(List.of(trelloBoardDto));

        // When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        // Then
        Assertions.assertEquals(1, trelloBoardList.size());
        Assertions.assertEquals(2, trelloBoardList.get(0).getLists().size());
        Assertions.assertEquals("Trello List Dto 1", trelloBoardList.get(0).getLists().get(0).getName());
        Assertions.assertEquals("Trello List Dto 2", trelloBoardList.get(0).getLists().get(1).getName());
        Assertions.assertFalse(trelloBoardList.get(0).getLists().get(0).isClosed());
        Assertions.assertFalse(trelloBoardList.get(0).getLists().get(1).isClosed());

    }

    @Test
    void shouldMapTrelloBoardListToTrelloBoardListDto() {
        // given
        TrelloList trelloList = new TrelloList("1", "Trello List 1", false);
        TrelloList trelloList2 = new TrelloList("2", "Trello List 2", false);
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "Trello Board 1", new ArrayList<>(
                List.of(trelloList, trelloList2)
        ));
        List<TrelloBoard> trelloBoardList = new ArrayList<>(List.of(trelloBoard1));

        // when
        List<TrelloBoardDto> trelloBoardListDto = trelloMapper.mapToBoardsDto(trelloBoardList);

        // then
        Assertions.assertEquals(1, trelloBoardListDto.size());
        Assertions.assertEquals(2, trelloBoardListDto.get(0).getLists().size());
        Assertions.assertEquals("1", trelloBoardListDto.get(0).getLists().get(0).getId());
        Assertions.assertEquals("Trello List 1", trelloBoardListDto.get(0).getLists().get(0).getName());
        Assertions.assertFalse(trelloBoardListDto.get(0).getLists().get(1).isClosed());
    }
    @Test
    void shouldMapTrelloListDtoToTrelloList() {
        // given
        TrelloListDto trelloListDto = new TrelloListDto("1", "Trello list 1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "Trello list 2", false);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>(List.of(
                trelloListDto, trelloListDto2
        ));

        // when
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        // then
        Assertions.assertTrue(trelloLists.get(0).isClosed());
        Assertions.assertFalse(trelloLists.get(1).isClosed());
        Assertions.assertEquals("1", trelloLists.get(0).getId());
        Assertions.assertEquals("Trello list 2", trelloLists.get(1).getName());
        Assertions.assertEquals(2, trelloLists.size());
    }

    @Test
    void shouldMapTrelloListToTrelloListDto() {
        // given
        TrelloList trelloList = new TrelloList("abc", "qwert", false);
        TrelloList trelloList2 = new TrelloList("abc2", "qwert2", false);
        List<TrelloList> trelloLists = new ArrayList<>(List.of(
                trelloList, trelloList2
        ));

        // when
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        // then
        Assertions.assertFalse(trelloListDtos.get(0).isClosed());
        Assertions.assertFalse(trelloListDtos.get(1).isClosed());
        Assertions.assertEquals(2, trelloListDtos.size());
        Assertions.assertEquals("abc", trelloListDtos.get(0).getId());
        Assertions.assertEquals("abc2", trelloListDtos.get(1).getId());
        Assertions.assertEquals("qwert", trelloListDtos.get(0).getName());
        Assertions.assertEquals("qwert2", trelloListDtos.get(1).getName());
    }

    @Test
    void shouldMapTrelloCardToTrelloCardDto() {
        // given
        TrelloCard trelloCard = new TrelloCard("test name", "test description", "test",
                "test id");

        // when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        // then
        Assertions.assertEquals("test name", trelloCardDto.getName());
        Assertions.assertEquals("test description", trelloCardDto.getDescription());
        Assertions.assertEquals("test", trelloCardDto.getPos());
        Assertions.assertEquals("test id", trelloCardDto.getListId());
    }

    @Test
    void shouldMapTrelloCardDtoToTrelloCard() {
        // given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test dto name", "test dto desc",
                "test dto pos", "test dto id");

        // when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        // then
        Assertions.assertEquals("test dto name", trelloCard.getName());
        Assertions.assertEquals("test dto desc", trelloCard.getDescription());
        Assertions.assertEquals("test dto pos", trelloCard.getPos());
        Assertions.assertEquals("test dto id", trelloCard.getListId());
    }
}
