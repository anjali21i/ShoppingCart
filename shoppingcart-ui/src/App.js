import logo from "./logo.svg";
import "./App.css";
import Cart from "./Components/Cart";
import { Product } from "./Components/Product";
import axios from "axios";
import { useEffect, useState } from "react";
import { calculateTotalPrice, djAr } from "./Utils/DummyData";

function App() {
  const [data, setData] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const [prodList, setProdList] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8085/api/getAllProductData');
        setData(response);
      } catch (error) {
        setData(djAr);
        setError(error);
      }
    };
    fetchData();
  });
  const cartDataCallback = (selectedProductList) => {
    console.log("cartDataCallback:", selectedProductList);
    setProdList({ ...selectedProductList });
    setTotalPrice(calculateTotalPrice(selectedProductList));
    // cartDataCallback
  };

  return (
    <div className="App font-bold">
      <div className="flex items-center">
        <Product productList={data} cartDataListCallBack={cartDataCallback} />
      </div>
      <Cart selectedProductList={prodList} totalPrice={totalPrice} />
    </div>
  );
}

export default App;
