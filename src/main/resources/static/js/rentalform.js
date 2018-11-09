

class Rental extends React.Component {
    constructor(props) {
        super(props);
        this.state = {firstname: '', lastname: '', email: '', phone: '', startday:'', endday: '', rental: '', message: ''};
    }

    inputChanged = (event) => {
    this.setState({[event.target.name]: event.target.value});
    }

    addTodo = (event) => {
    event.preventDefault();
    this.submitRental();
    }



    submitRental = () => {
        const url = 'http://localhost:8080/rentals';
        const data = { firstName: this.state.firstname,
        lastName: this.state.lastname};

        fetch(url, {
        method: 'POST',
        body: JSON.stringify(data), // data can be `string` or {object}!
        headers: {'Content-Type': 'application/json'}
        })
        .then((response) => response.json())

        .then((responseData) => {
        console.log(responseData);
        this.setState({
        message: "Success",
        rental: responseData.id});
        })
        .then(response => console.log('Success:', JSON.stringify(response)))
        .catch(error => console.error('Error:', error));
    }


    render() {
        return (
            <div>

                <form onSubmit={this.addTodo}>

                    <p><input type="text" name="firstname" placeholder="First name" value={this.state.firstname} onChange={this.inputChanged} /></p>
                    <p><input type="text" name="lastname" placeholder="Last name" value={this.state.lastname} onChange={this.inputChanged} /></p>
                    <p><input type="text" name="email" placeholder="Email" value={this.state.email} onChange={this.inputChanged} /></p>
                    <p><input type="text" name="phone" placeholder="Phone" value={this.state.phone} onChange={this.inputChanged} /></p>
                    <p><input type="text" name="startday" placeholder="Start day" value={this.state.startday} onChange={this.inputChanged} /></p>
                    <p><input type="text" name="endday" placeholder="End day" value={this.state.endday} onChange={this.inputChanged} /></p>

                    <input type="submit" value="Add"/>
                </form>


                <p>{this.state.message}</p>
                <p>Rental ID: {this.state.rental}</p>

            </div>
        );
    }
}

ReactDOM.render(<Rental />, document.getElementById('root'));
/*

const domContainer = document.querySelector('#root');
ReactDOM.render(e(Rental), domContainer);
*/