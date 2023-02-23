let allTotal = 0;
function addToCart(element) {

    //sledeca 4 leta uzimamo potrebne informacije
    let mainEl = element.closest('.single-item')
    let price = mainEl.querySelector('.price').innerText;
    let name = mainEl.querySelector('h3').innerText;
    let quantity = mainEl.querySelector('input').value;

    /* sad hvatamo div cart-items 
    jer cemo u njega da dodajemo proizvode*/
    let cartItems = document.querySelector('.cart-items');

    //dodavanje u korpu
    if (parseInt(quantity) > 0) {

        price = price.substring(1); //jer cena pocinje sa dolarom
        let total = parseInt(quantity) * parseInt(price);

        cartItems.innerHTML += `<div class ="cart-single-item">
                                    <h3>${name}</h3>
                                    <p> $${price} x ${quantity} = $${total}</p>
                                    <button onclick="removeFromCart(this)" class ="remove-item">Ukloni</button>
                                </div>`;
        element.innerText = 'dodato';
        element.setAttribute('disabled', 'true'); //blokiramo dugme

        //ispisujemo sve ukupno
        allTotal += total;
        document.querySelector('.total').innerText = `UKUPNO: ${allTotal}`;

    } else {
        alert('odaberi kolicinu(' + name + ')!');
    }

}



function removeFromCart(element) {
    let mainEl = element.closest('.cart-single-item');
    let price = mainEl.querySelector('p').innerText;

    //alternativno mogli bismo u prethodnoj funkciji
    //da dodamo span oko total price i onda lako mozemo
    // da ga targetujemo ili alternativno ko ja ovo dole

    let index$ = price.lastIndexOf('$');
    price = parseInt(price.substring(index$ + 1));
    allTotal -= price;
    document.querySelector('.total').innerText = `UKUPNO: ${allTotal}`;

    mainEl.remove();

    //ako obrisem iz karta, treba da aktiviram opet unosenje
    //tj da odblokiram bugme i da stavim input na 0

    let name = mainEl.querySelector('h3').innerText;
    let vegetables = document.querySelectorAll('.single-item');

    vegetables.forEach(function (vege) {
        let itemName = vege.querySelector('.si-content h3').innerText;

        if (itemName === name) {

            vege.querySelector('.actions input').value = 0;
            vege.querySelector('.actions button').removeAttribute('disabled');
            vege.querySelector('.actions button').innerText = "Dodaj";
        }

    });
}