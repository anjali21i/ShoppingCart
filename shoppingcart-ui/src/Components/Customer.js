import React, { useState } from "react";

const Customer = ({userDetailCallback}) => {
  const [userData, setUserData] = useState({
    "name":"",
    "email": ""
  })
  const onchangeData = (e) => {
    if(e.target.name=="name") {
      setUserData({
        ...userData,
        "name":e.target.value,
      })
    }
    if(e.target.name=="email") {
      setUserData({
        ...userData,
        "email":e.target.value,
      })
    }
    userDetailCallback(userData);

  }
  return (
    <div className="-mx-8">
      <div>
        User :<input type="text" name="name" value={userData.name} placeholder="enter name" onChange={onchangeData} />
      </div>
      <div>
        email : <input type="email" name="email" value={userData.email} placeholder="enter email" onChange={onchangeData}/>
      </div>
    </div>
  );
};

export default Customer;
