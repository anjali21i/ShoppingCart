const dJ = {
    pid:1,
    pname: "soap",
    pdesc: "",
    pQty: 5
}

export const djAr = [
    {
        pid:1,
        pname: "soap",
        pdesc: "jjk",
        pprice: 200,
        pQty: 5
    },
    {
        pid:2,
        pname: "Comb",
        pdesc: "cmb",
        pprice: 80,
        pQty: 5
    },
    {
        pid:3,
        pname: "bottle",
        pdesc: "a",
        pprice: 500,
        pQty: 3
    }
]

export const calculateTotalPrice = (productList) => {
    let totalPrice = 0;
    Object.keys(productList).forEach((index)=> {
        totalPrice = totalPrice + calculatePerPrice(productList[index].pprice, productList[index].selQty );
    })
    console.log(totalPrice);
    return totalPrice;
}
export const calculatePerPrice = (perPrice, qty) => {
    return perPrice*qty;
}
