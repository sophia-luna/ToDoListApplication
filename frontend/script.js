var taskList=[
    {
        name: "study",
        description: "read act 2 of Romeo and Juliet",
        date: "2023-09-07",
        category: "school",
        priority: "4",
        status: "Doing"
    },
    {
        name: "clean",
        description: "clean my room",
        date: "2023-09-08",
        category: "house",
        priority: "3",
        status: "To do"
    },
    {
        name: "meeting",
        description: "meeting with my boss at 2pm",
        date: "2023-09-09",
        category: "work",
        priority: "5",
        status: "To do"
    },
    {
        name: "doctor's appointment",
        description: "dermatologist appointment at 9am",
        date: "2023-09-06",
        category: "health",
        priority: "5",
        status: "Done"
    }
];

// keep task list always updated
function updateList(){
    if(taskList.size==0){
        document.getElementById("list-notif").innerHTML="<h2>*your to-do list is empty</h2>";
    }
    else{
        document.getElementById("list-notif").innerHTML="<h2>Tasks:</h2>";
        var li="<ol>";
        for(let i=0; i<taskList.length; i++){
            li+="<li> "+taskList[i].name+" </li>"
        }
        li+="</ol>";
        document.getElementById("task-list").innerHTML=li;
    }
}

//monitor status filter events
document.addEventListener("DOMContentLoaded", function () {
    // Get the filter select element
    var filter = document.getElementById("filter");

    // Add an event listener to the select element
    filter.addEventListener("change", function () {
        // Get the selected option value
        var selectedValue = filter.value;

        if(selectedValue=="all"){
            updateList();
        }
        else{
            document.getElementById("list-notif").innerHTML="<h2>Tasks:</h2>";
            var li="<ol>";
            for(let i=0; i<taskList.length; i++){
                if(taskList[i].status==selectedValue){
                    li+="<li> "+taskList[i].name+" </li>"
                }
            }
            li+="</ol>";
            document.getElementById("task-list").innerHTML=li;
        }
        
    });
});

function updateSelectTask(){
    if(taskList.size==0){
        document.getElementById("see-task-selection").innerHTML='<option value="none">None</option>';
        document.getElementById("alter-task-selection").innerHTML='<option value="none">None</option>';
        document.getElementById("delete-task-selection").innerHTML='<option value="none">None</option>';
    }
    else{
        var selection='<option value="none">None</option> ';
        for(let i=0; i<taskList.length; i++){
            selection+='<option value="'+taskList[i].name+'">'+taskList[i].name+'</option> ';
        }
        document.getElementById("see-task-selection").innerHTML=selection;
        document.getElementById("alter-task-selection").innerHTML=selection;
        document.getElementById("delete-task-selection").innerHTML=selection;
    }
}

//monitor task filter events
document.addEventListener("DOMContentLoaded", function () {
    // Get the filter select element
    var taskSelected = document.getElementById("see-task-selection");

    // Add an event listener to the select element
    taskSelected.addEventListener("change", function () {
        // Get the selected option value
        var selectedValue = taskSelected.value;
        
        if(selectedValue!="none"){
            var details=""

            var selectedTask=taskList.find(task => task.name == selectedValue);

            details+='<p>task name: ' + selectedTask.name + '</p> </br>';
            details+='<p>task description: ' + selectedTask.description + '</p> </br>';
            details+='<p>task date: ' + selectedTask.date + '</p> </br>';
            details+='<p>task category: ' + selectedTask.category + '</p> </br>';
            details+='<p>task priority: ' + selectedTask.priority + '</p> </br>';
            details+='<p>task status: ' + selectedTask.status + '</p> </br>';
                
            document.getElementById("task-details").innerHTML=details;
            
        }
        else{
            document.getElementById("task-details").innerHTML="";
        }
        
    });
});

// Add an event listener to the "Add task" button
addButton.addEventListener('click', function() {

    var nameInput = document.getElementById('add-name');
    var descriptionInput = document.getElementById('add-description');
    var dueDateInput = document.getElementById('add-due-date');
    var categoryInput = document.getElementById('add-category');
    var prioritySelect = document.getElementById('add-priority');
    var statusSelect = document.getElementById('add-status');

    var name = nameInput.value;
    var description = descriptionInput.value;
    var dueDate = dueDateInput.value;
    var category = categoryInput.value;
    var priority = prioritySelect.value;
    var status = statusSelect.value;

     var task = {
        name: name,
        description: description,
        date: dueDate,
        category: category,
        priority: priority,
        status: status
    };
    
    if(taskList.find(existingTask => existingTask.name == task.name))
    {
        alert("Task name already been used. Pick a different name or delete the existing task with the same name.")
    }
    else{
        taskList.push(task);
    }
    update();

});

alterButton.addEventListener('click', function(){

    var taskName = document.getElementById("alter-task-selection").value;
    var newName = document.getElementById("alter-name").value;
    var newDescription = document.getElementById("alter-description").value;
    var newDueDate = document.getElementById("alter-due-date").value;
    var newCategory = document.getElementById("alter-category").value;
    var newPriority = document.getElementById("alter-priority").value;
    var newStatus = document.getElementById("alter-status").value;

    var taskToUpdate = taskList.find(task => task.name == taskName);

    if(newName!="default") taskToUpdate.name = newName;
    if(newDescription!="default") taskToUpdate.description = newDescription;
    if(newDueDate!="0001-01-01") taskToUpdate.date = newDueDate;
    if(newCategory!="default") taskToUpdate.category = newCategory;
    if(newPriority!="default") taskToUpdate.priority = newPriority;
    if(newStatus!="default") taskToUpdate.status = newStatus;

    update();

});

deleteButton.addEventListener('click', function(){

    var taskSelected = document.getElementById("delete-task-selection").value;
    for(var i=0; i<taskList.length && taskList[i].name!=taskSelected; i++){}
    taskList.splice(i,1);
    update();

})

function update(){
    document.getElementById("task-details").innerHTML="";
    updateList();
    updateSelectTask();
}

update();