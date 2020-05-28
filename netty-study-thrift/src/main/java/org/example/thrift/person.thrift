namespace java org.example.thrift

typedef i16 short
typedef i32 int
typedef i64 long
typedef string String

struct PersonData{
    1: optional int num1=0,
    2: optional String comment
}

exception MyException{
    1: int whatOp,
    2: String why
}

exception NewException{
    1:long code,
    2:String message
}

service CaculatorService{

    string ping(),

    i32 add(1:int num1,2:int num2)

    i64 calculate(1:int num1,2:int num2,3:PersonData data) throws (1:MyException ouch,2:NewException ex)

    PersonData getPerson(1:int num,2:string name)

}