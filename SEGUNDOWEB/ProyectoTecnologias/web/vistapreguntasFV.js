const {
  Button,
  Icon,
  Typography,
  Paper,
  withStyles,
  Grid,
  ButtonBase,
  CheckboxGroup,Checkbox,CircleCheckBox
} = window['material-ui'];

const styles = theme => ({
    
  root: {
    textAlign: 'left',
    backgroundColor: '#FE6B8A',
    padding: theme.spacing.unit,
    paddingTop: theme.spacing.unit*6,
    paddingBottom: theme.spacing.unit *39,
    height: '100%',
    overflowX: 'auto'
  },
  icon: {
    margin: theme.spacing.unit,
    fontSize: 32,
  },
  image: {
    position: 'relative',
    maxHeight: 200,
    maxWidth: 500
  },
  main: {
    width: 'auto',
    display: 'block', // Fix IE 11 issue.
    marginLeft: theme.spacing.unit * 3,
    marginRight: theme.spacing.unit * 3,
    [theme.breakpoints.up(400 + theme.spacing.unit * 3 * 2)]: {
      width: 400,
      marginLeft: 'auto',
      marginRight: 'auto',
    },
  },
  paper: {
    padding: theme.spacing.unit * 20,
    paddingTop: theme.spacing.unit * 2,
    paddingBottom: theme.spacing.unit * 4,
    

    margin: 'center',
  //  minWidth: 50,
    height: '100%',
    backgroundColor: '#e1bee7',
    overflowX: 'auto'
  },
  casilla: {
    padding: theme.spacing.unit * 1,
    margin: 'center',
    minWidth: 50,
    backgroundColor: '#e1bee7',
    overflowX: 'auto'
  },
  button: {
    margin: theme.spacing.unit,
  },
});
class Index extends React.Component {
    
    constructor(props){
      super(props);
      this.state = {
          id: "",
          pregunta: "",
     //     respuestas:[],
          multimedia: "",
          tipo: "",
          codMulti: ""
      }
    }
    
    tipodeMultimedia(tipo){
        const { classes } = this.props;
        console.log("Se llamó a la función, tipo es: "+tipo);
        if(tipo==="jpg" || tipo==="png"){
                return (<ButtonBase className={classes.image}> <img className={classes.img} src={this.state.multimedia} /> </ButtonBase>);
            }else if(tipo==="mp4" || tipo==="MP4" ){
                return (<video controls> <source src={this.state.multimedia} type='video/mp4' /> </video>);
            }else if(tipo==="mp3" || tipo==="MP3"){
                return (<audio controls> <source src={this.state.multimedia} type='audio/mpeg' /> </audio>);
            }
    }
    
    componentDidMount = () => {
      fetch("preguntamc.json", {
          method: "GET"
      }).then(response => response.json())
        .then( json =>{
            console.log(json);
            var x = json.multimedia.substring(json.multimedia.length -3, json.multimedia.length);
            console.log(x);
            this.setState ({
                id: json.id,
                pregunta: json.pregunta,
             //   respuestas: json.resp,
                multimedia: json.multimedia,
                tipo: x
      });return x;});
  }
  
  render() {
    const {classes} = this.props;
    return (
     <div>   
      <Paper className={classes.root}>    

      <Paper className={classes.paper}>    
 
              
                <Typography gutterBottom variant="h6">
                  {this.state.id}
                </Typography>
                <Typography variant="h5">{this.state.pregunta}</Typography>
              
              <Grid container item xs={24} spacing={24}>
                    <Grid item xs={8}>
                      <Paper className={classes.casilla}>
                        <Grid container direction="row" alignItems="center">
                            <Grid item>
                                <Checkbox color="#FFF" />
                            </Grid>
                            <Grid item>
                                <Typography variant="body1">Verdadero</Typography>
                            </Grid>
                        </Grid>
                      </Paper>
                    </Grid>
                  </Grid>
               
                  
                  <Grid container item xs={24} spacing={24}>
                    <Grid item xs={8}>
                      <Paper className={classes.casilla}>
                        <Grid container direction="row" alignItems="center">
                            <Grid item>
                                <Checkbox color="#FFF" />
                            </Grid>
                            <Grid item>
                                <Typography variant="body1">Falso</Typography>
                            </Grid>
                        </Grid>
                      </Paper>
                    </Grid>
                  </Grid>
                  <br /><br />  
           
          <Grid item justify="center">
              {this.tipodeMultimedia(this.state.tipo)}
          </Grid>

    
       </Paper>
       
      <a href="tableQuestions.html">
            <Button fullWidth variant="contained" color="#FFF">Regresar</Button>
      </a>
      </Paper>
       </div> 
    );
  }
}

const VistaFV  = withStyles(styles)(Index);
ReactDOM.render(<VistaFV/>, document.getElementById('root'));

