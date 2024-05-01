document.addEventListener('DOMContentLoaded', function() {
  // Retrieve goals from localStorage when the DOM is fully loaded
  var savedGoals = JSON.parse(localStorage.getItem('goals')) || [];

  // Display saved goals on the page
  var goalList = document.getElementById('goal-list');
  savedGoals.forEach(function(goalText) {
    addGoalToDOM(goalText);
  });

  // Function to add a goal item to the DOM
  function addGoalToDOM(goalText) {
    var li = document.createElement('li');
    li.textContent = goalText;
    
    // Create a delete button (small "x" character)
    var deleteButton = document.createElement('button');
    deleteButton.innerHTML = '&times;'; // Unicode for "x" character
    deleteButton.classList.add('delete-button');
    deleteButton.addEventListener('click', function() {
      // Remove the goal from the DOM
      goalList.removeChild(li);
      // Remove the goal from localStorage
      var index = savedGoals.indexOf(goalText);
      if (index !== -1) {
        savedGoals.splice(index, 1);
        localStorage.setItem('goals', JSON.stringify(savedGoals));
      }
    });
    
    // Append the delete button to the goal item
    li.appendChild(deleteButton);
    
    // Append the goal item to the goal list
    goalList.appendChild(li);
  }

  // Add event listener to the form to save new goals
  document.getElementById('goal-form').addEventListener('submit', function(event) {
    event.preventDefault();
    var goalInput = document.getElementById('goal');
    var goalText = goalInput.value.trim();
    if (goalText !== '') {
      // Add new goal to the DOM
      addGoalToDOM(goalText);
      // Save the new goal to localStorage
      savedGoals.push(goalText);
      localStorage.setItem('goals', JSON.stringify(savedGoals));
      // Clear the input field
      goalInput.value = '';
    }
  });
});
