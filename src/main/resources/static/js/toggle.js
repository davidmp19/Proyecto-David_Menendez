const toggleButton = document.getElementById('darkModeToggle');
const htmlElement = document.documentElement;

document.addEventListener('DOMContentLoaded', () => {
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme) {
        htmlElement.setAttribute('data-bs-theme', savedTheme);
        updateToggleButton(savedTheme);
        document.body.style.visibility = 'visible';
    } else {
        localStorage.setItem('theme', htmlElement.getAttribute('data-bs-theme'));
    }
});

function updateToggleButton(theme) {
    if (theme === 'dark') {
        toggleButton.innerHTML = '<i class="bi bi-sun"></i> Modo Claro';
    } else {
        toggleButton.innerHTML = '<i class="bi bi-moon"></i> Modo Oscuro';
    }
}

toggleButton.addEventListener('click', () => {
    const isDarkMode = htmlElement.getAttribute('data-bs-theme') === 'dark';
    const newTheme = isDarkMode ? 'light' : 'dark';
    htmlElement.setAttribute('data-bs-theme', newTheme);
    localStorage.setItem('theme', newTheme);
    updateToggleButton(newTheme);
    document.body.style.visibility = 'visible'; 
});
