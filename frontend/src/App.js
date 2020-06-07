import React, {Component} from 'react';
import './App.css';
import {Song} from "./song/Song";
import {getSongsByArtist, getSongsByWord, getSongsByText} from "./utils/Requests";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            songs: [],
            artist: '',
            word: '',
            text: ''
        };

        this.handleChangeArtist = this.handleChangeArtist.bind(this);
        this.handleSubmitArtist = this.handleSubmitArtist.bind(this);

        this.handleChangeWord = this.handleChangeWord.bind(this);
        this.handleSubmitWord = this.handleSubmitWord.bind(this);

        this.handleChangeText = this.handleChangeText.bind(this);
        this.handleSubmitText = this.handleSubmitText.bind(this);

        this.reloadPage = this.reloadPage.bind(this);
    }


    displaySongsByArtist(value) {
        let songsList = null;

        getSongsByArtist(value)
            .then(response => {
                songsList = response
            })
            .finally(() => {
                this.setState({
                    songs: songsList
                })
            })

        this.setState({artist: ''})
    };

    handleChangeArtist(event) {
        this.setState({artist: event.target.value});
    }

    handleSubmitArtist(event) {
        this.displaySongsByArtist(this.state.artist)
        event.preventDefault()
    }

    ////////////////////////

    displaySongsByWord(value) {
        let songsList = null;

        getSongsByWord(value)
            .then(response => {
                songsList = response
            })
            .finally(() => {
                this.setState({
                    songs: songsList
                })
            })

        this.setState({word: ''})
    };

    handleChangeWord(event) {
        this.setState({word: event.target.value});
    }

    handleSubmitWord(event) {
        this.displaySongsByWord(this.state.word)
        event.preventDefault()
    }


    //////////////////////

    displaySongsByText(value) {
        let songsList = null;

        getSongsByText(value)
            .then(response => {
                songsList = response
            })
            .finally(() => {
                this.setState({
                    songs: songsList
                })
            })

        this.setState({text: ''})
    };


    handleChangeText(event) {
        this.setState({text: event.target.value});
    }

    handleSubmitText(event) {
        this.displaySongsByText(this.state.text)
        event.preventDefault()
    }

    //////////////////////

    reloadPage(event) {
        this.setState( {
            songs: [],
            artist: '',
            word: '',
            text: '' }
        )
    }

    render() {
        const result = this.state.songs.map(function (c) {
            return (
                <Song postId={c.id} artist={c.artist} song={c.song}
                      link={c.link} text={c.text}/>
            );
        });

        return (
            <div className="centered">
                <h1>Piosenki: </h1>
                Wpisz Artyste <input type="text" value={this.state.artist} onChange={this.handleChangeArtist}/>
                <button onClick={this.handleSubmitArtist}>Sprawdź</button>

                Wpisz Slowo <input type="text" value={this.state.word} onChange={this.handleChangeWord}/>
                <button onClick={this.handleSubmitWord}>Sprawdź</button>

                Wpisz Tekst <input type="text" value={this.state.text} onChange={this.handleChangeText}/>
                <button onClick={this.handleSubmitText}>Sprawdź</button>

                <button type="button" onClick={ this.reloadPage }>
                    <span>Odśwież</span>
                </button>

                {result}
            </div>
        )
    }
}

export default App;
