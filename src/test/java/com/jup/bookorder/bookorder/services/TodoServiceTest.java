package com.jup.bookorder.bookorder.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class TodoServiceTest {
	
	/*@InjectMocks
	TodoService todoService;
	
	@Mock
	TodoRepo todoRepo;
	
	User mockTodo;
	
	List<User> mockTodoList;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		mockTodo = new User();
		mockTodo.setId(1l);
		mockTodo.setTitle("mockTitle");
		mockTodo.setData("mockData");
		
		mockTodoList = new ArrayList<User>();
		mockTodoList.add(mockTodo);
	}
	
	@Test
	public void testGetTodoById_success(){
		when(todoRepo.findOne(anyLong())).thenReturn(mockTodo);
		User actual = todoService.getTodoById(1l);
		verify(todoRepo).findOne(1l);
		Assert.assertEquals(mockTodo, actual);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testGetTodoById_withNullObect(){
		when(todoRepo.findOne(anyLong())).thenReturn(null);
		User actual = todoService.getTodoById(1l);
		verify(todoRepo).findOne(1l);
	}
	
	@Test
	public void testGetTodoAll(){
		when(todoRepo.findAll()).thenReturn(mockTodoList);
		List<User> actual = todoService.getTodoAll();
		verify(todoRepo).findAll();
		Assert.assertEquals(mockTodoList, actual);
	}
	
	@Test
	public void testGetTodoPage(){
		TodoService todoServiceSpy = Mockito.spy(todoService);
		todoServiceSpy.getTodoPage(1, 20);
		verify(todoServiceSpy).generatePageRequest(1, 20);
	}
	
	@Test
	public void testSaveTodo(){
		ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
		when(todoRepo.save(argumentCaptor.capture())).thenReturn(mockTodo);
		
		todoService.saveTodo(mockTodo);
		
		Assert.assertEquals(null, argumentCaptor.getValue().getId());
		Assert.assertEquals(mockTodo.getTitle(), argumentCaptor.getValue().getTitle());
		Assert.assertEquals(mockTodo.getData(), argumentCaptor.getValue().getData());

		verify(todoRepo).save(any(User.class));
	}
	
	@Test
	public void testDeleteTodo(){
		when(todoRepo.findOne(anyLong())).thenReturn(mockTodo);
		Mockito.doNothing().when(todoRepo).delete(anyLong());
		
		todoService.deleteTodo(1l);
		
		verify(todoRepo).findOne(1l);
		verify(todoRepo).delete(1l);
	}
	
	@Test
	public void testDeleteTodo_noData(){
		when(todoRepo.findOne(anyLong())).thenReturn(null);
		Mockito.doNothing().when(todoRepo).delete(anyLong());
		
		todoService.deleteTodo(1l);
		
		verify(todoRepo).findOne(1l);
		verify(todoRepo, Mockito.never()).delete(1l);
	}
	
	@Test
	public void testUpdateTodoSuccess(){
		ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
		
		when(todoRepo.findOne(anyLong())).thenReturn(mockTodo);
		when(todoRepo.save(argumentCaptor.capture())).thenReturn(mockTodo);
		
		User updatedTodo = new User();
		updatedTodo.setId(1l);
		updatedTodo.setTitle("NewTitle");
		updatedTodo.setData("NewData");
		
		todoService.updateTodo(updatedTodo);
		
		verify(todoRepo).findOne(mockTodo.getId());
		verify(todoRepo).save(any(User.class));
		
		User actual = argumentCaptor.getValue();
		Assert.assertEquals(updatedTodo.getId(), actual.getId());
		Assert.assertEquals(updatedTodo.getTitle(), actual.getTitle());
		Assert.assertEquals(updatedTodo.getData(), actual.getData());
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testUpdateTodo_TodoNoId(){
		ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
		
		when(todoRepo.findOne(anyLong())).thenReturn(mockTodo);
		when(todoRepo.save(argumentCaptor.capture())).thenReturn(mockTodo);
		
		User updatedTodo = new User();
		updatedTodo.setId(null);
		updatedTodo.setTitle("NewTitle");
		updatedTodo.setData("NewData");
		
		todoService.updateTodo(updatedTodo);
		
		
		verify(todoRepo, Mockito.never()).findOne(mockTodo.getId());
		verify(todoRepo, Mockito.never()).save(any(User.class));
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testUpdateTodo_NoObject(){
		ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
		
		when(todoRepo.findOne(anyLong())).thenReturn(null);
		when(todoRepo.save(argumentCaptor.capture())).thenReturn(mockTodo);
		
		User updatedTodo = new User();
		updatedTodo.setId(1l);
		updatedTodo.setTitle("NewTitle");
		updatedTodo.setData("NewData");
		
		todoService.updateTodo(updatedTodo);
		
		
		verify(todoRepo).findOne(mockTodo.getId());
		verify(todoRepo, Mockito.never()).save(any(User.class));
	}
	@Test
	public void testGeneratePageRequest(){
		PageRequest actual = todoService.generatePageRequest(1, 20);
		Assert.assertEquals(1, actual.getPageNumber());
		Assert.assertEquals(20, actual.getPageSize());
	}
	
	@Test
	public void testGeneratePageRequest_numberAndSizeIsNull(){
		PageRequest actual = todoService.generatePageRequest(null, null);
		Assert.assertEquals(0, actual.getPageNumber());
		Assert.assertEquals(99, actual.getPageSize());
	}*/
}
