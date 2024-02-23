function openImage(src) {
    console.log('Image clicked:', src); // Log the clicked image source
    // Create a modal element
    var modal = document.createElement('div');
    modal.classList.add('modal');
    
    // Create an image element inside the modal
    var modalImg = document.createElement('img');
    modalImg.src = src;
    
    // Append the image to the modal
    modal.appendChild(modalImg);
    
    // Append the modal to the body
    document.body.appendChild(modal);
}
