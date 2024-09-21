package com.toDoList.todoapp.controller;

import com.toDoList.todoapp.domain.Todo;
import com.toDoList.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepository todoRepository;


    @GetMapping("/")
    public String index(Model model) {
        List<Todo> todos = todoRepository.findAll();
        model.addAttribute("todos",todos);
        return "todos";
    }

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam("todo")String todo){
        //데이터베이스에 저장하면 된다.
        Todo toDo = new Todo();
        toDo.setTodo(todo);
        todoRepository.save(toDo);
        return "redirect:/";
    }
    @PostMapping("deleteTodo")
    public String deleteTodo(@RequestParam("id")Long id){

        todoRepository.deleteById(id);
        return "redirect:/";

    }
    @PostMapping("/doneTodo")
    public String doneTodo(@RequestParam("id") Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Todo ID: " + id));

        todo.setDone(true);  // 완료 상태로 변경
        todoRepository.save(todo);  // 상태 업데이트 후 저장

        return "redirect:/";  // 변경 후 목록 페이지로 리다이렉트
    }

}
