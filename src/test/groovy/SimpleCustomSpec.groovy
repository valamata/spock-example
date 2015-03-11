import org.h2.engine.Role
import org.h2.engine.User
import spock.lang.Ignore
import spock.lang.Specification
import sun.nio.fs.UnixUserPrincipals

/**
 * Created by qude on 1/17/15.
 */
class SimpleCustomSpec extends Specification {

    @Ignore()
    def "should return two fro first element of list"() {
        given:
        List<Integer> list = new ArrayList<>();

        when:
        list.add(1)
        list.add(3)
        then:
        2 == list.get(0)
    }

    @Ignore()
    def "should calculate power of number 2"() {
        expect:
        Math.pow(2, 2) == 6
    }

    def "creating simple stub example"() {
        given:
        List list = Stub()
    }

    def "specifyng return value in a stub"() {  //use >> operator to specify rer=turn value

        given:
        List<Integer> list = Stub()
        list.size() >> 3
        expect:
        list.size() == 3

    }

    def "specifying side effects when stub method is executed"(){  //just put closure after right shift operator
        given: List<Integer> list= Stub()
               list.size() >> {
                   println "size method has been invoked  "
               }


    }

    def "specifying that exception should be thrown"(){

        given:List<Integer> list =Stub()
        list.size() >> {
            throw  new IllegalStateException()
        }

    }

    def "should return different value based on invocation order"(){
        given:List<Integer> list =Stub()
            list.size() >>> [1,2,3]
        expect:list.size()==1

    }

    def "should return different values or throw expectaction"(){
        given:List<Integer> list =Stub()
        list.size() >> {
            throw  new IllegalStateException()} >>>[1,2,3] >> {new IllegalStateException() } >>>[5,6]

    }

    /**
     *  whenever the updateRoleAndReturnPreviousOne()
     method is being invoked with one parameter:
     execute following action (closure after right shift operator)
     or return following value (value after right shift operator).
     *
     *
      */
    @Ignore
    def "should act differntly based on the condition" (){
        given:User user= Stub()
          user.updateRoleAndReturnPreviousOne(_) >>{Role role ->
              if(Role.ADmin==role){
                  throw new IllegalArgumentException()
              }else{
                  return Role.USER
              }
          }
    }


}


