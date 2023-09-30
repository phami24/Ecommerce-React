function HomeSignup() {
  return (
    <div className="flex flex-col w-full h-[35rem] bg-no-repeat bg-cover bg-[url('https://amfissa.qodeinteractive.com/wp-content/uploads/2021/10/parallax-img1.jpg')] bg-opacity-25">
      <main className="container mx-auto px-6 mt-20 pt-16 flex-1 text-center">
        <h2 className="font-marsf text-sm md:text-4xl lg:text-5xl text-white mb-2">
          Sign Up For Special Promotions
        </h2>
        <h2 className="font-marsf text-sm md:text-4xl lg:text-5xl text-white mb-20">
          & Updates
        </h2>

        <form
          action="https://www.getrevue.co/profile/tyler_potts_/add_subscriber"
          method="post"
          id="revue-form"
          name="revue-form"
          target="_blank"
          className="flex flex-col md:flex-row justify-center mb-4 font-inherit"
        >
        
          <button className="bg-black w-full md:w-[10rem] relative py-2.5 px-5 font-medium uppercase text-white transition-colors overflow-hidden">
            <span className="absolute left-0 top-0 z-[-1] h-full w-full origin-top-left scale-y-0 bg-gray-300 transition-transform duration-300"></span>
            SIGN UP
          </button>
        </form>
      </main>
    </div>
  );
}

export default HomeSignup;
