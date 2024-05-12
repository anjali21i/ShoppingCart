import axios from "axios";
import React, { useEffect, useState } from "react";
import { calculatePerPrice } from "../Utils/DummyData";

export const Product = ({ productList, cartDataListCallBack }) => {
  // const [dataVal, setDataVal] = useState({});
  const [qunt, setQunt] = useState(0);
  const [cartData, setCartData] = useState([]);
  // const [perPrice, setPerPrice] = useState(dataVal.price);
  // const [error, setError] = useState("");

  // useEffect(() => {
  //   // const fetchData = async () => {
  //   //   // try {
  //   //   //   const response = await axios.get(
  //   //   //     "http://localhost:8085/api/getProductData"
  //   //   //   );
  //   //   //   setDataVal(response);
  //   //   // } catch (error) {
  //   //   //   setError(error);
  //   //   // }
  //   // };
  //   // fetchData();
  // }, [dataVal]);

  const addDataToList = (e, val) => {
    // console.log("inside v",e);
    // console.log("inside before cartd",val);
    const price = calculatePerPrice(val.pprice,qunt);
    val = {
      ...val,
      selQty: qunt,
      subprice: price
    }
    console.log("cartD after",val);
    cartData.push(val);
    console.log("cartD after p",cartData);
    // setCartData(...cartData);
    cartDataListCallBack(cartData);
    setQunt(0);
  };

  const updateQuantity = (value, data) => {
    // console.log("updateQuantity",value);
    // console.log(data);
    setQunt(value);
  };

  return (
    <div className="box ">
      Product
      <div className="flex items-center rounded-xl border border-x-8">
        <table>
          <tr  key={-1} className="border">
            <th className="border">product no</th>
            <th className="border">product name</th>
            <th className="border">price</th>
            <th className="border">quantity</th>
            <th className="border">Add to Cart</th>
          </tr>
          {Object.keys(productList).map((val, index) => {
            return (
              <tr id={productList[index].pid} className="border" key={index}>
                <td>{productList[index].pid}</td>
                <td>{productList[index].pname}</td>
                <td>{productList[index].pprice}</td>
                <td>
                  <InputData initQ={qunt} pname={productList[index].pid} setQuantityCallback={updateQuantity}/>
                </td>
                <td className="bg-yellow-950 max-w-lg">
                  <button 
                    name="add"
                    className="max-w-lg shadow-lg space-y-2 rounded bg-yellow-400 w-28 hover:bg-yellow-600 "
                    onClick={(e) => addDataToList(e, productList[index])}
                  >
                    Add
                  </button>
                </td>
              </tr>
            );
          })}
        </table>
      </div>
    </div>
  );
};

const InputData = ({pname, setQuantityCallback, initQ}) => {
  const [quantity, setQuantity] = useState(initQ);
  // console.log(initQ)

  const updateQuantity = (e) => {
    setQuantity(e.target.value);
    setQuantityCallback(e.target.value, e);
  }

  return (
    <input
      name={pname}
      className="border"
      value={quantity}
      type="number"
      onChange={(e) =>updateQuantity(e)}
    ></input>
  )
}
