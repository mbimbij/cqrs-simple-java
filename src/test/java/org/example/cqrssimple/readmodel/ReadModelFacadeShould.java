package org.example.cqrssimple.readmodel;

import org.example.cqrssimple.event.ItemCheckedInEvent;
import org.example.cqrssimple.event.ItemCreatedEvent;
import org.example.cqrssimple.event.ItemDeactivatedEvent;
import org.example.cqrssimple.event.ItemRemovedEvent;
import org.example.cqrssimple.event.ItemRenamedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ReadModelFacadeShould {

    private ReadModelFacade readModelFacade;
    private EventPublisher eventPublisher;

    @BeforeEach
    void setUp() {
        FakeDatabaseItemList fakeDatabaseItemList = new FakeDatabaseItemList();
        FakeDatabaseItemDetails fakeDatabaseItemDetails = new FakeDatabaseItemDetails();
        readModelFacade = new ReadModelFacade(fakeDatabaseItemList, fakeDatabaseItemDetails);
        eventPublisher = new EventPublisher();
        eventPublisher.subscribe(new ItemListView(fakeDatabaseItemList));
        eventPublisher.subscribe(new ItemDetailsView(fakeDatabaseItemDetails));
    }

    @Nested
    public class ItemList {
        @Test
        void returnEmptyItemList_whenNoItemCreated() {
            // WHEN
            Collection<ItemListDto> itemList = readModelFacade.getItemList();

            // THEN
            assertThat(itemList).isEmpty();
        }

        @Test
        void return2Items_whenItemCreatedEventSent_for2Items() {
            // GIVEN
            UUID uuid1 = UUID.randomUUID();
            String itemName1 = "item1";
            UUID uuid2 = UUID.randomUUID();
            String itemName2 = "item2";

            eventPublisher.publish(List.of(
                    new ItemCreatedEvent(uuid1, itemName1),
                    new ItemCreatedEvent(uuid2, itemName2)
            ));

            // WHEN
            Collection<ItemListDto> itemList = readModelFacade.getItemList();

            // THEN
            assertThat(itemList).containsExactly(
                    new ItemListDto(uuid1.toString(), itemName1),
                    new ItemListDto(uuid2.toString(), itemName2)
            );
        }

        @Test
        void return1Item_whenCreate2Items_andRemove1() {
            // GIVEN
            UUID uuid1 = UUID.randomUUID();
            String itemName1 = "item1";
            UUID uuid2 = UUID.randomUUID();
            String itemName2 = "item2";

            eventPublisher.publish(List.of(
                    new ItemCreatedEvent(uuid1, itemName1),
                    new ItemCreatedEvent(uuid2, itemName2),
                    new ItemDeactivatedEvent(uuid1)
            ));

            // WHEN
            Collection<ItemListDto> itemList = readModelFacade.getItemList();

            // THEN
            assertThat(itemList).containsExactly(
                    new ItemListDto(uuid2.toString(), itemName2)
            );
        }

        @Test
        void handleItemRenamedEvent() {
            // GIVEN
            UUID uuid = UUID.randomUUID();
            String oldName = "name";
            String newName = "new name";

            eventPublisher.publish(List.of(
                    new ItemCreatedEvent(uuid, oldName),
                    new ItemRenamedEvent(uuid, newName)
            ));

            // WHEN
            Collection<ItemListDto> itemList = readModelFacade.getItemList();

            // THEN
            assertThat(itemList).containsExactly(
                    new ItemListDto(uuid.toString(), newName)
            );
        }
    }

    @Nested
    public class ItemDetails {

        private UUID uuid;
        private String itemName = "name";

        @BeforeEach
        void setUp() {
            uuid = UUID.randomUUID();
        }

        @Test
        void returnEmpty_whenItemNotCreatedYet() {
            Optional<ItemDetailsDto> itemDetailsDto = readModelFacade.getItemDetails(uuid);
        }

        @Test
        void returnItemsDetails_whenItemsCreated() {
            // GIVEN
            eventPublisher.publish(List.of(new ItemCreatedEvent(uuid, itemName)));

            // WHEN
            Optional<ItemDetailsDto> itemDetailsDtoOptional = readModelFacade.getItemDetails(uuid);

            // THEN
            ItemDetailsDto expectedItemDetailsDto = new ItemDetailsDto(uuid.toString(), itemName, 0);
            assertThat(itemDetailsDtoOptional.get()).usingRecursiveComparison().isEqualTo(expectedItemDetailsDto);
        }

        @Test
        void returnUpdatedItemsDetails_whenItemCheckedIn() {
            // GIVEN
            int checkedInQuantity = 2;
            eventPublisher.publish(List.of(
                    new ItemCreatedEvent(uuid, itemName),
                    new ItemCheckedInEvent(uuid, checkedInQuantity)
            ));

            // WHEN
            Optional<ItemDetailsDto> itemDetailsDtoOptional = readModelFacade.getItemDetails(uuid);

            // THEN
            ItemDetailsDto expectedItemDetailsDto = new ItemDetailsDto(uuid.toString(), itemName, 2);
            assertThat(itemDetailsDtoOptional.get()).usingRecursiveComparison().isEqualTo(expectedItemDetailsDto);
        }

        @Test
        void returnUpdatedItemsDetails_whenItemRemoved() {
            // GIVEN
            int checkedInQuantity = 5;
            int removeQuantity = 2;
            eventPublisher.publish(List.of(
                    new ItemCreatedEvent(uuid, itemName),
                    new ItemCheckedInEvent(uuid, checkedInQuantity),
                    new ItemRemovedEvent(uuid, removeQuantity)
            ));

            // WHEN
            Optional<ItemDetailsDto> itemDetailsDtoOptional = readModelFacade.getItemDetails(uuid);

            // THEN
            ItemDetailsDto expectedItemDetailsDto = new ItemDetailsDto(uuid.toString(), itemName, 3);
            assertThat(itemDetailsDtoOptional.get()).usingRecursiveComparison().isEqualTo(expectedItemDetailsDto);
        }

        @Test
        void returnUpdatedItemsDetails_whenItemRenamed() {
            // GIVEN
            String newName = "new name";
            eventPublisher.publish(List.of(
                    new ItemCreatedEvent(uuid, itemName),
                    new ItemRenamedEvent(uuid, newName)
            ));

            // WHEN
            Optional<ItemDetailsDto> itemDetailsDtoOptional = readModelFacade.getItemDetails(uuid);

            // THEN
            ItemDetailsDto expectedItemDetailsDto = new ItemDetailsDto(uuid.toString(), newName, 0);
            assertThat(itemDetailsDtoOptional.get()).usingRecursiveComparison().isEqualTo(expectedItemDetailsDto);
        }

        @Test
        void returnNoItem_whenItemDeactivated() {
            // GIVEN
            eventPublisher.publish(List.of(
                    new ItemCreatedEvent(uuid, itemName),
                    new ItemDeactivatedEvent(uuid)
            ));

            // WHEN
            Optional<ItemDetailsDto> itemDetailsDtoOptional = readModelFacade.getItemDetails(uuid);

            // THEN
            assertThat(itemDetailsDtoOptional).isEmpty();
        }
    }
}