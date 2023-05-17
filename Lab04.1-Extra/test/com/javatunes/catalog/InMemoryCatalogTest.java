package com.javatunes.catalog;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class InMemoryCatalogTest {
    // object under test - "fixture"
    private InMemoryCatalog catalog;

    @Before
    public void setUp() {
        catalog = new InMemoryCatalog();
    }

    @Test
    public void findByKeyword_shouldReturn() {

    }

    @Test
    public void testSize() {
        assertEquals(18, catalog.size());
    }

    @Test
    public void findByCategory_shouldReturnNull_whenCategoryNotFound() {
        Collection<MusicItem> nullItem = catalog.findByCategory(MusicCategory.JAZZ);
        for (MusicItem item : nullItem) {
            assertNull(item.getMusicCategory());
        }
    }

    @Test
    public void findByCategory_shouldReturnPopulatedCollection_whenCategoryFound() {
        Collection<MusicItem> popItems = catalog.findByCategory(MusicCategory.POP);
        for (MusicItem item : popItems){
            assertEquals(MusicCategory.POP, item.getMusicCategory());
        }
        // all MusicItems in this collection should have Category POP
    }

    @Test
    public void findById_shouldReturnNull_idNotFound() {
        MusicItem item = catalog.findById(19L);
        assertNull(item);
    }

    @Test
    public void findById_shouldReturnMusicItemWithThatId_whenIdFound() {
        MusicItem item = catalog.findById(6L);
        assertEquals(6L, item.getId().longValue());
    }
}