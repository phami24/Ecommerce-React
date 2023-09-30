import React, { useState, useEffect } from "react";
import { TextareaAutosize } from "@mui/base/TextareaAutosize";
import PhoneEnabledIcon from "@mui/icons-material/PhoneEnabled";
import MailOutlineIcon from "@mui/icons-material/MailOutline";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function Contact() {
  const [showSuccess, setShowSuccess] = useState(false); // Track success state

  const [data, setData] = useState({
    name: "",
    email: "",
    message: "",
  });

  // Handle form input changes
  function handleInputChange(e) {
    const { id, value } = e.target;
    setData((prevData) => ({
      ...prevData,
      [id]: value,
    }));
  }

  // Handle form submission
  function submitForm(e) {
    e.preventDefault();
    if (!data.name || !data.email || !data.message) {
      // Hiển thị thông báo lỗi nếu một trường nào đó bị bỏ trống
      toast.error("🚫 Please fill in all required fields.", {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
      return; // Dừng việc gửi nếu có trường bị bỏ trống
    }

    // Replace with your API endpoint URL
    const url = "http://localhost:1303/api/contact/add";

    axios
      .post(url, {
        name: data.name,
        email: data.email,
        message: data.message,
      })
      .then((res) => {
        setShowSuccess(true);
        toast.success("🦄 Form submitted successfully!", {
          position: "top-right",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "light",
        });
      })
      .catch((error) => {
        console.error("Error sending data:", error);
      });
  }

  useEffect(() => {
    axios
      .post("http://localhost:1303/api/contact/add")
      .then((res) => {
        setData(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <div>
      <div className="flex flex-col  w-full h-[20rem] bg-no-repeat bg-cover bg-[url('https://assets.website-files.com/63708df071bc73798fae96ee/63721c7fa9a62db2c4796074_slider-2.jpg')] bg-opacity-25">
        <main className="container mx-auto px-6 mt-20 pt-16 flex-1 text-center">
          <h2 className="font-marsf text-sm md:text-4xl lg:text-5xl text-white mb-2">
            Contact Us
          </h2>
        </main>
      </div>
      {showSuccess}
      <ToastContainer />
      <div className="antialiased mt-28">
        <div className="flex w-full ml-60   p-8 ">
          <div className="w-[60%] space-y-9">
            <h2 className="font-marsf text-[34px] text-[#000000]">
              Get in touch!
            </h2>
            <p className="font-san text-[18px] text-[#6c6d6a]">
              Integer ac interdum lacus. Nunc porta semper lacus a varius.
              Pellentesque habitant morbi tristique senectus et netus et
              malesuada fames ac turpis egestas.
            </p>
            <p className="font-san text-[18px] text-[#6c6d6a]">
              Nunc sagittis consectetur velit, ac gravida nunc gravida et.
              Vestibulum at eros imperdiet, volutpat nunc vitae, ornare erat.
              Proin interdum aliquet porta. Fusce ut semper ligula.
            </p>
            <div className="flex just space-x-32 ">
              <div className="space-y-4 ">
                <span className="font-san text-[18px]  text-[#6c6d6a]">
                  <PhoneEnabledIcon className="text-orange-500" />
                  (374)635-9332
                </span>
                <div className="flex">
                  <MailOutlineIcon className="text-xs text-orange-500  " />
                  <span className="font-san text-[18px]  text-[#6c6d6a]">
                    amfissa@example.com
                  </span>
                </div>
                <div className="flex">
                  <LocationOnIcon className="text-orange-500" />
                  <span className="font-san text-[18px]  text-[#6c6d6a]">
                    6705 Main Wood Street, Long Beach, California
                  </span>
                </div>
              </div>

              <div className="space-y-4 ">
                <span className="font-san text-[18px]  text-[#6c6d6a]">
                  <PhoneEnabledIcon className="text-orange-500" />
                  (374)635-9332
                </span>
                <div className="flex">
                  <MailOutlineIcon className="text-xs text-orange-500  " />
                  <span className="font-san text-[18px]  text-[#6c6d6a]">
                    amfissa@example.com
                  </span>
                </div>
                <div className="flex">
                  <LocationOnIcon className="text-orange-500" />
                  <span className="font-san text-[18px]  text-[#6c6d6a]">
                    6705 Main Wood Street, Long Beach, California
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div className="w-full ml-36 mr-60 space-y-4">
            <h2 className="font-marsf text-[34px] text-[#000000]">
              We love to hear from you!
            </h2>
            <form onSubmit={submitForm}>
              <div className="w-[80%] mb-8 ">
                <h1 className="font-marsf  text-[#000000] my-2 text-[20px]">
                  Name*
                </h1>
                <input
                  id="name"
                  value={data.name}
                  onChange={handleInputChange}
                  placeholder="Enter your name *"
                  type="text"
                  className=" outline-none border-solid w-full border border-gray-300  focus:border-black border:1  p-3"
                />
              </div>

              <div className="w-[80%] ">
                <h1 className="font-marsf my-2 text-[#000000] text-[20px]">
                  Email*
                </h1>
                <input
                  id="email"
                  value={data.email}
                  onChange={handleInputChange}
                  placeholder="Your Email *"
                  type="text"
                  className=" outline-none		border-solid	  w-full border border-gray-300  focus:border-black border:1  p-3"
                />
              </div>
              <div className="w-[80%] space-y-3">
                <h1 className="mt-8 font-marsf text-[#000000] text-[20px]">
                  Message*
                </h1>
                <TextareaAutosize
                  id="message"
                  value={data.message}
                  onChange={handleInputChange}
                  minRows={8}
                  maxRows={12}
                  placeholder="Write your message"
                  className="outline-none	 w-full border border-gray-300 rounded-md p-3"
                />
              </div>
              <button className="bg-black w-[10rem] mt-6 relative py-2.5 px-5 font-medium uppercase text-white transition-colors overflow-hidden">
                <span className="absolute left-0 top-0 z-[-1] h-full w-full origin-top-left scale-y-0 bg-gray-300 transition-transform duration-300"></span>
                SUBMIT
              </button>
            </form>
          </div>
        </div>
      </div>
      <div className="w-full h-full">
        <iframe
          src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.740295837024!2d105.84755407600525!3d21.003044988659806!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ac748cd9d447%3A0xaf1371c17f07550c!2zMTkgUC4gTMOqIFRoYW5oIE5naOG7iywgQuG6oWNoIE1haSwgSGFpIELDoCBUcsawbmcsIEjDoCBO4buZaSwgVmlldG5hbQ!5e0!3m2!1sen!2s!4v1691868358495!5m2!1sen!2s"
          width="1900"
          height="450"
          // style="border:0"
          allowfullscreen=""
          loading="lazy"
          referrerpolicy="no-referrer-when-downgrade"
        ></iframe>
      </div>
    </div>
  );
}

export default Contact;
