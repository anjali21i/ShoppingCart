import React, { useEffect, useState } from "react";
import Customer from "./Customer";
import axios from "axios";

export const Cart = ({ selectedProductList, totalPrice }) => {
  const [message, setMessage] = useState("");
  const [userDetail, setUserDetail] = useState({
    username:"",
    email:""
  })

  const [error, setError] = useState("");
  useEffect(() => {}, []);

  const submitDataToCart = (event) => {
    event.preventDefault();
    if (selectedProductList) {
      console.log("submit:",userDetail); 
      console.log("selectedProductList:",selectedProductList);
      const orderData = {
        selectedProductList,
        userDetail
      }
      console.log(orderData)
      axios
        .post("/api/cart/submitDataToCart", { selectedProductList })
        .then((response) => {
          setMessage("Product added to cart successfully!");
        })
        .catch((error) => {
          setMessage("Error adding product to cart!");
        });
    }
  };
  const userDetailCallback = (userData) => {
    console.log("ud:",userData);
    setUserDetail(userData);
  };
  return (
    <div className="border-spacing-8">
      <h2>Cart</h2>
      <div className="bg-slate-100 border">
        <table className="border content-between">
          <tr className="border" >
            <th>PID</th>
            <th>Product</th>
            <th>Quantity</th>
            <th>Amount</th>
          </tr>
          {Object.keys(selectedProductList).map((val, index) => {
            return (
              <tr className="border-spacing-1 bg-white" key={index}>
                <td>{selectedProductList[index].pid}</td>
                <td>{selectedProductList[index].pname}</td>
                <td>{selectedProductList[index].selQty}</td>
                <td>{selectedProductList[index].subprice}</td>
              </tr>
            );
          })}
        </table>
        <CartTotalPrice totalPrice={totalPrice ? totalPrice : 0} />
      </div>
      <Customer userDetailCallback={userDetailCallback} />
      <button
        className="border rounded-xl bg-blue-400 w-28 hover:bg-blue-700 shadow-lg space-y-2"
        type="submit"
        onSubmit={submitDataToCart}
        onClick={submitDataToCart}
      >
        Place Order
      </button>
      <p>{message}</p>
    </div>
  );
};

export default Cart;

const CartTotalPrice = ({ totalPrice }) => {
  return (
    <div>
      <h2 className="inline-block">Total Price in Cart: ${totalPrice}</h2>
    </div>
  );
};
