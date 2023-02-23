let izracunajBtn = document.querySelector('button[type="submit"]');

let newElement = document.createElement('div');
newElement.className = 'new-value';
newElement.innerHTML = "";

izracunajBtn.addEventListener('click', e => {
  e.preventDefault();

  let inflationRate = document.querySelector("#inflationRate").value;
  let money = document.querySelector("#money").value;
  let years = document.querySelector("#years").value;



  if (inflationRate === "" || money === "" || years === "") {
    newElement.innerHTML = "Morate popuniti sva polja";
  } else {
    inflationRate = parseFloat(inflationRate);
    money = parseFloat(money);
    years = parseFloat(years);

    let worth = money + money * (inflationRate / 100);

    for (let i = 1; i < years; i++) {
      worth += worth * (inflationRate / 100);
    }

    newElement.innerText = `Danasnjih ${money} vredi isto kao ${worth.toFixed(2)}e za ${years} godina`;
    izracunajBtn.setAttribute('disabled', 'true');
    resetujBtn.removeAttribute('disabled');
  }

  document.querySelector('.container').appendChild(newElement);




});

let resetujBtn = document.querySelector('button[type="reset"]');
resetujBtn.addEventListener('click', e => {
  document.querySelector("#inflationRate").value = '';
  document.querySelector("#money").value = '';
  document.querySelector("#years").value = '';

  let newElement = document.querySelector('.new-value');
  newElement.remove();

  resetujBtn.setAttribute('disabled', 'true');
  izracunajBtn.removeAttribute('disabled');
});

//Donji nacin radi... samo treba u formi staviti onclick() --> ali to je stari nacin

// function inflationCalculator() {
//   let inflationRate = document.querySelector("#inflationRate").value;
//   let money = document.querySelector("#money").value;
//   let years = document.querySelector("#years").value;

//   inflationRate = parseFloat(inflationRate);
//   money = parseFloat(money);
//   years = parseFloat(years);

//   let worth = money + money * (inflationRate / 100);

//   for (let i = 1; i < years; i++) {
//     worth += worth * (inflationRate / 100);
//   }


//   console.log("Worth: " + worth)
//   let newElement = document.createElement('div');
//   newElement.className = 'new-value';
//   newElement.innerText = `Danasnjih ${money} vredi isto kao ${worth.toFixed(2)}e za ${years} godina`;

//   document.querySelector('.container').appendChild(newElement);
//   console.log("Ispisujem rezultat");


// }

// function izbrisi() {
//   //ako stavim .value = '' bice prazno polje a npr ako stavim .value = 0 upisace nulu... svejedno
//   document.querySelector("#inflationRate").value = '';
//   document.querySelector("#money").value = '';
//   document.querySelector("#years").value = '';
//   let newElement = document.querySelector('.new-value');
//   if (newElement == null) {
//     console.log("Ne brisem rezultat, jer ga nema");
//   } else {
//     newElement.remove();
//     console.log("Brisem rezultate");


//   }

// }
