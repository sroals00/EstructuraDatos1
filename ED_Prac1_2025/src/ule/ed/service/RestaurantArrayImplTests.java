package ule.ed.service;

import org.junit.*;
import java.util.List;
import java.util.Arrays;

public class RestaurantArrayImplTests {

    private RestaurantArrayImpl res;

    @Before
    public void testBefore() throws Exception {
        res = new RestaurantArrayImpl("Casa Pepe", 15, 100, 10);
    }

    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Casa Pepe", res.getName());
    }

    @Test
    public void testGetMaxCapacity() throws Exception {
        Assert.assertEquals(100, res.getMaxCapacity());
    }

    @Test
    public void testGetNumberOfChildren() throws Exception {
        Assert.assertEquals(0, res.getNumberOfChildren());
        res.occupyTable(4, 2);
        Assert.assertEquals(2, res.getNumberOfChildren());
    }

    @Test
    public void testGetNumberOfPeople() throws Exception {
        Assert.assertEquals(0, res.getNumberOfPeople());
        res.occupyTable(4, 2);
        Assert.assertEquals(4, res.getNumberOfPeople());
    }

    @Test
    public void testGetActualCapacity() throws Exception {
        Assert.assertEquals(100, res.getActualCapacity());
        res.occupyTable(4, 2);
        Assert.assertEquals(96, res.getActualCapacity());
    }

    @Test
    public void testGetNumberTablesOccupied() throws Exception {
        Assert.assertEquals(0, res.getNumberTablesOccupied());
        res.occupyTable(4, 2);
        Assert.assertEquals(1, res.getNumberTablesOccupied());
    }

    @Test
    public void testGetNumberOfEmptyTables() throws Exception {
        Assert.assertEquals(15, res.getNumberOfEmptyTables());
        res.occupyTable(4, 2);
        Assert.assertEquals(14, res.getNumberOfEmptyTables());
    }

    @Test
    public void testGetNumberOfTablesWithChildren() throws Exception {
        Assert.assertEquals(0, res.getNumberOfTablesWithChildren());
        res.occupyTable(4, 2);
        Assert.assertEquals(1, res.getNumberOfTablesWithChildren());
    }

    @Test
    public void testGetNumbersOfEmptyTables() throws Exception {
        List<Integer> emptyTables = res.getNumbersOfEmptyTables();
        Assert.assertEquals(15, emptyTables.size());
        Assert.assertTrue(emptyTables.containsAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)));
        res.occupyTable(4, 2);
        emptyTables = res.getNumbersOfEmptyTables();
        Assert.assertEquals(14, emptyTables.size());
        Assert.assertTrue(emptyTables.containsAll(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)));
    }

    @Test
    public void testGetService() throws Exception {
        Assert.assertNull(res.getService(1));
        res.occupyTable(4, 2);
        Assert.assertNotNull(res.getService(1));
    }

    @Test
    public void testAddDishToTable() throws Exception {
        res.occupyTable(4, 2);
        res.addDishToTable(1, "Arroz", 10.0, 2);
        Service service = res.getService(1);
        Assert.assertEquals(1, service.getOrder().size());
        Assert.assertEquals("Arroz", service.getOrder().get(0).getName());
        Assert.assertEquals(10.0, service.getOrder().get(0).getPrice(), 0.0);
        Assert.assertEquals(2, service.getOrder().get(0).getCount());
    }

    @Test
    public void testGetFinalPrice() throws Exception {
        res.occupyTable(4, 2);
        res.addDishToTable(1, "Arroz", 10.0, 2);
        Assert.assertEquals(18.0, res.getFinalPrice(1), 0.0);
    }

    @Test
    public void testGetFinalPriceRestaurant() throws Exception {
        res.occupyTable(4, 2);
        res.addDishToTable(1, "Arroz", 10.0, 2);
        Assert.assertEquals(18.0, res.getFinalPriceRestaurant(), 0.0);
    }

    @Test
    public void testEmptyTable() throws Exception {
        res.occupyTable(4, 2);
        Assert.assertTrue(res.emptyTable(1));
        Assert.assertNull(res.getService(1));
        Assert.assertEquals(100, res.getActualCapacity());
    }

    @Test
    public void testOccupyTable() throws Exception {
        Assert.assertEquals(1, res.occupyTable(4, 2));
        Assert.assertEquals(96, res.getActualCapacity());
        Assert.assertEquals(4, res.getNumberOfPeople());
        Assert.assertEquals(2, res.getNumberOfChildren());
    }

    @Test
    public void testOccupyTableWithSpecificTable() throws Exception {
        Assert.assertTrue(res.occupyTable(1, 4, 2));
        Assert.assertFalse(res.occupyTable(1, 4, 2)); // Mesa ya ocupada
    }
}

