
const {
  Button,
  Icon,
  Typography,
  Paper,
  withStyles,
  Grid,
  ButtonBase,
  Checkbox,Input,FormControl,InputLabel
} = window['material-ui'];



const styles = theme => ({
    
  root: {
    textAlign: 'center',
    backgroundColor: '#FE6B8A',
    padding: theme.spacing.unit,
    paddingTop: theme.spacing.unit*6,
    paddingBottom: theme.spacing.unit *39,
    height: '100%',
    overflowX: 'auto'
    
  },margin: {
    margin: 0,

  },
  icon: {
    margin: theme.spacing.unit,
    fontSize: 32,
  },
  image: {
    position: 'center',
    maxHeight: 200,
    maxWidth: 500
  },
  main: {
    width: 'auto',
    display: 'block', // Fix IE 11 issue.
    marginLeft: theme.spacing.unit * 3,
    marginRight: theme.spacing.unit * 3,
    
    [theme.breakpoints.up(400 + theme.spacing.unit * 3 * 2)]: {
      width: 4000,
      marginLeft: 'auto',
      marginRight: 'auto',
      
      
    },
  },
  paper: {
    padding: theme.spacing.unit * 20,
    paddingTop: theme.spacing.unit * 2,
    paddingBottom: theme.spacing.unit * 4,
    

 //   margin: 'auto',
  //  minWidth: 50,
    height: '100%',
    backgroundColor: '#e1bee7',
    overflowX: 'auto'
    
  //  background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
   },margin: {
    margin: 0,

  },
  
  button: {
    margin: theme.spacing.unit,
  },
  submit: {
    marginTop: theme.spacing.unit * 3,
  },
  leftButton: {
    marginTop: theme.spacing.unit*2,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing.unit,
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    paddingTop: theme.spacing.unit * 1000,
    paddingBottom: theme.spacing.unit * 1,
    paddingLeft: theme.spacing.unit * 50,
    paddingRight: theme.spacing.unit * 50,
  },
 
});
class Index extends React.Component {
    
    constructor(props){
      super(props);
      this.state = {
          id: "",
          pregunta: "",
          respuestas:[],
          multimedia: "",
          tipo: "",
          codMulti: ""
      }
    }
    
    tipodeMultimedia(tipo){
        const { classes } = this.props;
        console.log("Se llamó a la función, tipo es: "+tipo);
        if(tipo==="jpg" || tipo==="png" || tipo==="JPG"){
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
                respuestas: json.resp,
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
                <FormControl margin="normal"  fullWidth>
                <InputLabel >Escriba su respuesta* </InputLabel>
                <Input id="resp1" name="resp1" value = {this.state.resp1}/> 
                </FormControl>
                
 
          
          <Grid item justify="center">
              {this.tipodeMultimedia(this.state.tipo)}
          </Grid>

    
       </Paper>
       <a href="tableQuestions.html" >
            <Button fullWidth variant="contained" color= '#FFF' className={classes.leftButton}>Regresar</Button>
      </a>
  </Paper>
 
  </div>
    );
  }
}
const VerPattern = withStyles(styles)(Index);
ReactDOM.render(<VerPattern />, document.getElementById('root'));